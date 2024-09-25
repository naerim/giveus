package com.giveus.admin.domain.usage.application;

import com.giveus.admin.global.common.response.CreateSuccessDto;
import com.giveus.admin.domain.usage.dto.FundingUsageCreateReq;
import com.giveus.admin.domain.funding.domain.Funding;
import com.giveus.admin.domain.usage.domain.UsageHistory;
import com.giveus.admin.domain.usage.dao.UsageHistoryRepository;
import com.giveus.admin.domain.funding.application.FundingService;
import com.giveus.admin.global.error.exception.InvalidRequestException;
import com.giveus.admin.infra.sms.CoolSmsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UsageHistoryServiceImpl implements UsageHistoryService {
    private final UsageHistoryRepository usageHistoryRepository;
    private final FundingService fundingService;
    private final CoolSmsClient coolSmsClient;
    private final RestTemplate restTemplate;

    @Value("${notification.usageHistory-url}")
    private String usageHistoryUrl;

    @Override
    @Transactional
    public CreateSuccessDto createFundingUsage(List<FundingUsageCreateReq> reqList) {

        // 진행중인 펀딩이라면 사용 내역 등록 요청 시 예외 발생 처리
//        if (!fundingService.isDoneFunding(funding)) {
//            throw new InvalidRequestException();
//        }

        if (reqList.isEmpty()) { // 빈 배열이라면 예외 발생 시키기
            throw new InvalidRequestException();
        }

        Funding funding = fundingService.getFundingEntity(reqList.get(0).getFundingNo());
        for (FundingUsageCreateReq req : reqList) {

            // 펀딩 사용 내역 등록
            UsageHistory usageHistory = UsageHistoryTransfer.dtoToEntity(funding, req);
            usageHistoryRepository.save(usageHistory);
        }

        // 최초 등록일 시에 알림, 문자 전송
        if (getUsageHistoryList(funding).size() == reqList.size()) {
            try {
                // 펀딩 수혜자에게 후기 등록할 링크 문자 전송
                String msg =
                    "[giveus]\n" +
                    "안녕하세요, 기브어스입니다.\n" +
                    "아래의 링크에서 후원 후기를 나눠주세요!\n" +
                        "admin.giveus.site/giveus/review/";
                coolSmsClient.send(funding.getPhone(), msg, funding.getRegId());

                // 펀딩에 참여한 기부자들에게 알림 전송 (microservice간 통신)
                String requestUrl = usageHistoryUrl + funding.getFundingNo();
                restTemplate.exchange(
                        requestUrl, // 요청 URL
                        HttpMethod.POST, // 요청 메서드
                        null, // 요청 본문
                        String.class // 응답 타입
                );
            } catch (Exception e) {
                log.error(e.getMessage());
            }


        }

        return new CreateSuccessDto(funding.getFundingNo());
    }

    private List<UsageHistory> getUsageHistoryList(Funding funding) {
        return usageHistoryRepository.findByFunding(funding);
    }

}

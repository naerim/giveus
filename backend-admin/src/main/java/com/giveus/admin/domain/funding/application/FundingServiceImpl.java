package com.giveus.admin.domain.funding.application;

import com.giveus.admin.global.common.response.CreateSuccessDto;
import com.giveus.admin.domain.funding.dto.FundingCreateReq;
import com.giveus.admin.domain.funding.dto.FundingDetailsRes;
import com.giveus.admin.domain.funding.dto.FundingListRes;
import com.giveus.admin.domain.funding.domain.Funding;
import com.giveus.admin.domain.funding.domain.FundingStatusHistory;
import com.giveus.admin.domain.funding.exception.FundingNotFoundException;
import com.giveus.admin.domain.funding.dao.FundingRepository;
import com.giveus.admin.domain.funding.dao.FundingStatusHistoryRepository;
import com.giveus.admin.infra.sms.CoolSmsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FundingServiceImpl implements FundingService {
    private final FundingRepository fundingRepository;
    private final FundingStatusHistoryRepository fundingStatusRepository;
    private final CoolSmsClient coolSmsClient;

    @Override
    @Transactional
    public CreateSuccessDto createFunding(FundingCreateReq fundingCreateReq) {

        // 펀딩 등록
        Funding funding = FundingTransfer.dtoToEntity(fundingCreateReq);
        FundingStatusHistory status = FundingStatusHistoryTransfer.dtoToEntityCreated(funding);
        funding.addStatus(status);
        String regId = generateRegId();
        funding.setRegId(regId);
        Funding savedFunding = fundingRepository.save(funding);

        // 문자 전송
//        String msg = "[giveus]\n" +
//                "안녕하세요, 기브어스입니다.\n" +
//                "아래의 링크에서 펀딩 추가정보를 입력해주세요!\n" +
//                "https://giveus.site/giveus/";
//        coolSmsClient.send(funding.getPhone(), msg, regId);

        return new CreateSuccessDto(savedFunding.getFundingNo());
    }


    /**
     * 펀딩 등록 고유 ID를 생성하는 메서드입니다.
     *
     * @return 생성한 고유 ID
     */
    private String generateRegId() {
        String random = UUID.randomUUID().toString();
        random = random.replace("-", "");
        random = random.substring(0, 16);
        return random;
    }

    @Override
    public List<FundingListRes> getFundingList(int adminNo) {
        return fundingRepository.getFundingList(adminNo);
    }

    @Override
    public Funding getFundingEntity(int fundingNo) {
        return
                fundingRepository.findFundingByFundingNo(fundingNo)
                        .orElseThrow(FundingNotFoundException::new);
    }


    @Override
    public FundingDetailsRes getFunding(int fundingNo) {
        return fundingRepository.getFunding(fundingNo);
    }

    @Override
    public boolean isDoneFunding(Funding funding) {
        FundingStatusHistory status = fundingStatusRepository.findDistinctByFundingOrderByCreatedAtDesc(funding).get(0);
        return !status.getStatus().equals("진행중");
    }

}

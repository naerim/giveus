package com.giveus.notification.repository;

import com.giveus.notification.dto.response.FundingReviewListRes;
import com.giveus.notification.dto.response.UsageHistoryListRes;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface NotificationRepositoryCustom {
    List<FundingReviewListRes> getFundingReviewList(int fundingNo);

    List<UsageHistoryListRes> getUsageHistoryList(int fundingNo);
}

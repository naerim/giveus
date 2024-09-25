package com.giveus.admin.domain.funding.dao;

import com.giveus.admin.domain.funding.dto.FundingDetailsRes;
import com.giveus.admin.domain.funding.dto.FundingListRes;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author 이하늬
 * @since 1.0
 */
@NoRepositoryBean
public interface FundingRepositoryCustom {
    List<FundingListRes> getFundingList(int adminNo);

    FundingDetailsRes getFunding(int fundingNo);
}

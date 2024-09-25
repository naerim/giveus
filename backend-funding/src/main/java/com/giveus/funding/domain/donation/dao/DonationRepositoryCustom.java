package com.giveus.funding.domain.donation.dao;

import com.giveus.funding.domain.donation.dto.DonationAmountRes;
import com.giveus.funding.domain.donation.dto.DonationListRes;
import com.giveus.funding.domain.donation.dto.MemberDonationListRes;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author 이하늬
 * @since 1.0
 */
@NoRepositoryBean
public interface DonationRepositoryCustom {

    List<MemberDonationListRes> getMemberFundingList(int memberNo);


    List<DonationListRes> getParticipantListByFunding(int fundingNo);


    DonationAmountRes getDonationAmount();

    List<DonationListRes> getParticipantList(int limit);

}

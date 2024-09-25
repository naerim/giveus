package com.giveus.funding.domain.donation.application;

import com.giveus.funding.domain.donation.dao.DonationRepository;
import com.giveus.funding.domain.donation.dto.DonationAmountRes;
import com.giveus.funding.domain.donation.dto.DonationListRes;
import com.giveus.funding.domain.donation.dto.MemberDonationListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
private final DonationRepository donationRepository;

    @Override
    public List<DonationListRes> getDonationList(int limit) {
        return donationRepository.getParticipantList(limit);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<DonationListRes> getDonationListByFunding(int fundingNo) {
        return donationRepository.getParticipantListByFunding(fundingNo);
    }

    /**
     * @inheritDoc
     */
    @Override
    public DonationAmountRes getDonationAmount() {
        return donationRepository.getDonationAmount();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<MemberDonationListRes> getDonationListByMember(int memberNo) {
        return donationRepository.getMemberFundingList(memberNo);
    }
}

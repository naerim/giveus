package com.giveus.funding.domain.donation.application;

import com.giveus.funding.domain.donation.dto.DonationAmountRes;
import com.giveus.funding.domain.donation.dto.DonationListRes;
import com.giveus.funding.domain.donation.dto.MemberDonationListRes;

import java.util.List;

public interface DonationService {

    /**
     * 최근 기부 내역을 조회하는 메서드입니다.
     * 후원에 참여한 참여자 전체 목록을 조회합니다.
     *
     * @param limit 조회할 갯수
     * @return 조회한 참여자 전체 목록
     */
    List<DonationListRes> getDonationList(int limit);

    /**
     * 펀딩 참여자 내역을 조회하는 메서드입니다.
     *
     * @param fundingNo 조회할 펀딩 고유 번호
     * @return 펀딩 참여자 목록
     */
    List<DonationListRes> getDonationListByFunding(int fundingNo);

    /**
     * 누적 기부 금액을 조회하는 메서드입니다.
     *
     * @return 누적 기부 금액
     */
    DonationAmountRes getDonationAmount();


    /**
     * 회원 고유 번호로 기부에 참여한 펀딩 목록을 조회하는 메서드입니다.
     *
     * @param memberNo 조회할 회원 고유 번호
     * @return 조회한 펀딩 목록
     */
    List<MemberDonationListRes> getDonationListByMember(int memberNo);


}

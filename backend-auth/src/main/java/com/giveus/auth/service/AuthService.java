package com.giveus.auth.service;

import com.giveus.auth.dto.request.AuthJoinPostReq;
import com.giveus.auth.dto.response.MemberInfoRes;
import com.giveus.auth.entity.Member;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface AuthService {

    MemberInfoRes updateMember(AuthJoinPostReq userJoinPostReq);

    Optional<Member> findByProviderAndKey(String provider, String key);

    MemberInfoRes findByMemberNo(int memberNo);

    MemberInfoRes findByAccessToken(HttpServletRequest httpRequest);
}

package com.giveus.admin.domain.member.application;

import com.giveus.admin.domain.member.dto.AdminJoinPostReq;
import com.giveus.admin.domain.member.dto.AdminInfoRes;
import com.giveus.admin.domain.member.domain.Admin;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface AdminService {

    AdminInfoRes updateAdmin(AdminJoinPostReq adminJoinPostReq);

    Optional<Admin> findByProviderAndKey(String provider, String key);

    AdminInfoRes findByAdminNo(int adminNo);

    AdminInfoRes findByAccessToken(HttpServletRequest httpRequest);

}

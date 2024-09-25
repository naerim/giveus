package com.giveus.admin.domain.member.application;

import com.giveus.admin.global.util.JwtUtil;
import com.giveus.admin.domain.member.dto.AdminJoinPostReq;
import com.giveus.admin.domain.member.dto.AdminInfoRes;
import com.giveus.admin.domain.member.domain.Admin;
import com.giveus.admin.domain.member.exception.NoAdminExistException;
import com.giveus.admin.domain.member.dao.AuthAdminRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AuthAdminRepository authAdminRepository;

    private final JwtUtil jwtUtil;

    @Override
    public AdminInfoRes updateAdmin(AdminJoinPostReq adminJoinPostReq) {
        String email = adminJoinPostReq.getEmail();
        String provider = adminJoinPostReq.getProvider();

        log.info("email = {}, provider = {}", email, provider);
        Admin admin = authAdminRepository.findByProviderAndEmail(provider, email).orElseThrow(NoAdminExistException::new);
        admin.updateName(adminJoinPostReq.getName());
        admin.updateEthAddress(adminJoinPostReq.getEthAddress());

        authAdminRepository.save(admin);

        return AdminInfoRes.from(admin);
    }

    @Override
    public Optional<Admin> findByProviderAndKey(String provider, String key) {
        return authAdminRepository.findByProviderAndKey(provider, key);
    }

    @Override
    public AdminInfoRes findByAdminNo(int adminNo) {
        log.info("=== AuthServiceImpl - findByMemberNum === \n memberNo : {}", adminNo);
        Admin admin = authAdminRepository.findByAdminNo(adminNo).orElseThrow(NoAdminExistException::new);

        return AdminInfoRes.from(admin);
    }

    @Override
    public AdminInfoRes findByAccessToken(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");

        String provider = jwtUtil.getProvider(accessToken);
        String snsKey = jwtUtil.getSnsKey(accessToken);

        log.info("=== AuthServiceImpl - findByAccessToken === \n provider : {}, snsKey : {} ", provider, snsKey);
        Admin admin = authAdminRepository.findByProviderAndKey(provider, snsKey).orElseThrow(NoAdminExistException::new);

        return AdminInfoRes.from(admin);
    }

}

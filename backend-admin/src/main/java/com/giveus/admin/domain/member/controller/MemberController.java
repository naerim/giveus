package com.giveus.admin.domain.member.controller;

import com.giveus.admin.domain.member.application.AdminService;
import com.giveus.admin.domain.member.dto.AdminInfoRes;
import com.giveus.admin.domain.member.dto.AdminJoinPostReq;
import com.giveus.admin.global.common.response.CommonResponseBody;
import com.giveus.admin.global.config.annotaion.SwaggerApiSuccess;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "관리자 API", description = "Admin")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class MemberController {
    private final AdminService adminService;

    @SwaggerApiSuccess(summary = "병원 회원가입 추가 정보 입력", implementation = AdminInfoRes.class)
    @PutMapping("/join")
    public ResponseEntity<CommonResponseBody<AdminInfoRes>> updateMember(@RequestBody AdminJoinPostReq adminJoinPostReq) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, adminService.updateAdmin(adminJoinPostReq)));
    }

    @SwaggerApiSuccess(summary = "병원 정보 조회 (엑세스 토큰 이용)", implementation = AdminInfoRes.class)
    @GetMapping("/info")
    public ResponseEntity<CommonResponseBody<AdminInfoRes>> findByAccessToken(HttpServletRequest httpServletRequest) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, adminService.findByAccessToken(httpServletRequest)));
    }
}

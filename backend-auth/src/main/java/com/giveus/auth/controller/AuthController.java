package com.giveus.auth.controller;

import com.giveus.auth.common.dto.CommonResponseBody;
import com.giveus.auth.common.dto.CreateSuccessDto;
import com.giveus.auth.common.swagger.SwaggerApiSuccess;
import com.giveus.auth.dto.request.AuthJoinPostReq;
import com.giveus.auth.dto.request.MemberDevicePostReq;
import com.giveus.auth.dto.response.MemberInfoRes;
import com.giveus.auth.dto.response.NicknameExistRes;
import com.giveus.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    
    @SwaggerApiSuccess(summary = "회원가입 추가 정보 입력", implementation = MemberInfoRes.class)
    @PutMapping("/join")
    public ResponseEntity<CommonResponseBody<MemberInfoRes>> updateMember(@RequestBody AuthJoinPostReq userJoinPostReq) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, authService.updateMember(userJoinPostReq)));
    }

    @SwaggerApiSuccess(summary = "회원 정보 조회 (회원번호 이용)", implementation = MemberInfoRes.class)
    @GetMapping("/{memberNo}")
    public ResponseEntity<CommonResponseBody<MemberInfoRes>> findByMemberNo(@PathVariable int memberNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, authService.findByMemberNo(memberNo)));
    }

    @SwaggerApiSuccess(summary = "회원 정보 조회 (엑세스 토큰 이용)", implementation = MemberInfoRes.class)
    @GetMapping("/info")
    public ResponseEntity<CommonResponseBody<MemberInfoRes>> findByAccessToken(HttpServletRequest httpServletRequest) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, authService.findByAccessToken(httpServletRequest)));
    }

    @SwaggerApiSuccess(summary = "FCM 기기 토큰 저장", implementation = String.class)
    @PostMapping("/device")
    public ResponseEntity<CommonResponseBody<CreateSuccessDto>> createMemberDevice(@RequestBody MemberDevicePostReq memberDevicePostReq) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, authService.createMemberDevice(memberDevicePostReq)));
    }

    @SwaggerApiSuccess(summary = "FCM 기기 토큰 삭제", implementation = String.class)
    @DeleteMapping("/device")
    public ResponseEntity<CommonResponseBody<String>> deleteMemberDevice(@RequestParam int memberNo, @RequestParam String deviceToken) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, authService.deleteMemberDevice(memberNo, deviceToken)));
    }

    @SwaggerApiSuccess(summary = "닉네임 중복검사", implementation = NicknameExistRes.class)
    @GetMapping("/nickname")
    public ResponseEntity<CommonResponseBody<NicknameExistRes>> findByNickname(@RequestParam String nickname) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, authService.findByNickname(nickname)));
    }

}

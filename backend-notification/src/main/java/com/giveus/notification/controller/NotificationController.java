package com.giveus.notification.controller;

import com.giveus.notification.common.dto.CommonResponseBody;
import com.giveus.notification.common.dto.CreateSuccessDto;
import com.giveus.notification.common.swagger.SwaggerApiSuccess;
import com.giveus.notification.dto.response.MemberSettingInfoRes;
import com.giveus.notification.dto.response.NotificationListRes;
import com.giveus.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "알림 API", description = "Notification")
@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    // 알림 전체 목록 조회
    @SwaggerApiSuccess(summary = "알림 전체 조회", implementation = NotificationListRes.class)
    @GetMapping("/{memberNo}")
    public ResponseEntity<CommonResponseBody<List<NotificationListRes>>> getNotificationList(@PathVariable int memberNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, notificationService.getNotificationList(memberNo)));
    }

    // 알림 단건 삭제
    @SwaggerApiSuccess(summary = "알림 단일 삭제", implementation = String.class)
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Error.class)))
    @DeleteMapping("/{notificationNo}")
    public ResponseEntity<CommonResponseBody<String>> deleteNotification(@PathVariable int notificationNo) {
        notificationService.deleteNotification(notificationNo);
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, ""));
    }

    // 알림 전체 삭제
    @SwaggerApiSuccess(summary = "알림 전체 삭제", implementation = String.class)
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Error.class)))
    @DeleteMapping("/deleteAll/{memberNo}")
    public ResponseEntity<CommonResponseBody<String>> deleteAllNotification(@PathVariable int memberNo) {
        notificationService.deleteAllNotification(memberNo);
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, ""));
    }
    
    // 알림 단일 읽음 여부 변경
    @SwaggerApiSuccess(summary = "알림 단일 읽음 여부 변경", implementation = String.class)
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Error.class)))
    @PatchMapping("/{notificationNo}")
    public ResponseEntity<CommonResponseBody<String>> updateNotification(@PathVariable int notificationNo) {
        notificationService.updateNotification(notificationNo);
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, ""));
    }

    // 알림 전체 읽음 여부 변경
    @SwaggerApiSuccess(summary = "알림 전체 읽음 여부 변경", implementation = String.class)
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Error.class)))
    @PatchMapping("/changeAll/{memberNo}")
    public ResponseEntity<CommonResponseBody<String>> updateAllNotification(@PathVariable int memberNo) {
        notificationService.updateAllNotification(memberNo);
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, ""));
    }

    // 펀딩 후기 등록 시 알림 발송
    @SwaggerApiSuccess(summary = "펀딩 후기 등록 시 알림 발송", implementation = String.class)
    @PostMapping("/fundingReview/{fundingNo}")
    public ResponseEntity<CommonResponseBody<String>> createFundingReviewNotification(@PathVariable int fundingNo) {
        notificationService.createFundingReviewNotification(fundingNo);
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, "")); // 원래는 create는 pk 반환해야?ㅠㅠ
    }

    // 진료비 사용 내역 등록 시 알림 발송
    @SwaggerApiSuccess(summary = "펀딩 진료비 사용 내역 등록 시 알림 발송", implementation = String.class)
    @PostMapping("/usageHistory/{fundingNo}")
    public ResponseEntity<CommonResponseBody<String>> createUsageHistoryNotification(@PathVariable int fundingNo) {
        notificationService.createUsageHistoryNotification(fundingNo);
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, "")); // 원래는 create는 pk 반환해야?ㅠㅠ
    }

}

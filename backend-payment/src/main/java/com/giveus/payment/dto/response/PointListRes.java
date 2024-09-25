package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@ToString
@Schema(description = "포인트 충전/사용 내역 응답")
public class PointListRes {

    private List<PointUsageRes> usageList;
    private List<PointRechargeRes> rechargeList;

}

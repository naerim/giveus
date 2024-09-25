package com.giveus.gateway.common.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // DTO 를 JSON으로 변환 시 null값인 field 제외
public class StatusResponseRes {
    private Integer status;
    private Object data;

    public StatusResponseRes(Integer status) {
        this.status = status;
    }

    public static StatusResponseRes addStatus(Integer status) {
        return new StatusResponseRes(status);
    }

    public static StatusResponseRes success(){
        return new StatusResponseRes(200);
    }
    public static StatusResponseRes success(Object data){
        return new StatusResponseRes(200, data);
    }

}

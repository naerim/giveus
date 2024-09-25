package com.giveus.common.advisor;

import com.giveus.common.dto.CommonResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

/**
 * rest controller 에서 예외발생시 종합적인 처리를 해주기 위한 클래스입니다.
 *
 * @author 이하늬
 */
@RestControllerAdvice
@Slf4j
public class RestControllerAdvisor {

    /**
     * 400에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
//  @ExceptionHandler
//  public ResponseEntity<CommonResponseBody<String>> badRequestException400(Exception e) {
//
//    return ResponseEntity
//        .status(BAD_REQUEST)
//        .body(new CommonResponseBody<>(BAD_REQUEST, e.getMessage()));
//  }

    /**
     * 401에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
//    @ExceptionHandler(value = {})
//    public ResponseEntity<CommonResponseBody<String>> unauthorizedException401(Exception e) {
//
//        return ResponseEntity
//                .status(UNAUTHORIZED)
//                .body(new CommonResponseBody<>(UNAUTHORIZED, e.getMessage()));
//    }

    /**
     * 403에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
//    @ExceptionHandler(value = {})
//    public ResponseEntity<CommonResponseBody<String>> forbiddenException403(RuntimeException e) {
//
//        return ResponseEntity
//                .status(FORBIDDEN)
//                .body(new CommonResponseBody<>(FORBIDDEN, e.getMessage()));
//    }

    /**
     * 404에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
//    @ExceptionHandler(value = {})
//    public ResponseEntity<CommonResponseBody<String>> NotFoundException404(RuntimeException e) {
//
//        return ResponseEntity
//                .status(NOT_FOUND)
//                .body(new CommonResponseBody<>(NOT_FOUND, e.getMessage()));
//    }

    /**
     * 409에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
//    @ExceptionHandler(value = {})
//    public ResponseEntity<CommonResponseBody<String>> ConflictException409(RuntimeException e) {
//
//        return ResponseEntity.status(CONFLICT)
//                .body(new CommonResponseBody<>(CONFLICT, e.getMessage()));
//
//    }


    /**
     * 500에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResponseEntity<CommonResponseBody<String>> internalErrorException500(Exception e) {

        log.error(e.getMessage());

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new CommonResponseBody<>(INTERNAL_SERVER_ERROR, e.getMessage()));

    }

}

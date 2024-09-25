package com.giveus.notification.common.advisor;

import com.giveus.notification.common.dto.CommonResponseBody;
import com.giveus.notification.common.dto.ErrorResponseDto;
import com.giveus.notification.exception.NotificationDeleteFailedException;
import com.giveus.notification.exception.NotificationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * rest controller 에서 예외발생시 종합적인 처리를 해주기 위한 클래스입니다.
 */
@RestControllerAdvice
public class RestControllerAdvisor {
    /*

     */
/**
 * 400에 해당하는 예외들을 한번에 처리하는 메소드입니다.
 *
 * @param e 실제 발생한 예외객체입니다.
 * @return 에러메세지를 response entity 에 담아서 전송합니다.
 *//*

//  @ExceptionHandler
//  public ResponseEntity<BaseResponseBody> badRequestException400(Exception e) {
//
//    return ResponseEntity
//        .status(BAD_REQUEST)
//        .body(BaseResponseBody.of(BAD_REQUEST, e.getMessage()));
//  }

    */
/**
 * 401에 해당하는 예외들을 한번에 처리하는 메소드입니다.
 *
 * @param e 실제 발생한 예외객체입니다.
 * @return 에러메세지를 response entity 에 담아서 전송합니다.
 *//*

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> unauthorizedException401(Exception e) {

        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(new ErrorResponseDto(UNAUTHORIZED, e.getMessage()));
    }

    */
/**
 * 403에 해당하는 예외들을 한번에 처리하는 메소드입니다.
 *
 * @param e 실제 발생한 예외객체입니다.
 * @return 에러메세지를 response entity 에 담아서 전송합니다.
 *//*

//  @ExceptionHandler(value = {})
//  public ResponseEntity<BaseResponseBody> forbiddenException403(RuntimeException e) {
//
//    return ResponseEntity
//        .status(FORBIDDEN)
//        .body(BaseResponseBody.of(FORBIDDEN, e.getMessage()));
//  }

    */

    /**
     * 404에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
    @ExceptionHandler({NotificationNotFoundException.class})
    public ResponseEntity<CommonResponseBody<String>> NotFoundException404(RuntimeException e) {

        return ResponseEntity
                .status(NOT_FOUND)
                .body(new CommonResponseBody<>(NOT_FOUND, e.getMessage()));
    }


/**
 * 409에 해당하는 예외들을 한번에 처리하는 메소드입니다.
 *
 * @param e 실제 발생한 예외객체입니다.
 * @return 에러메세지를 response entity 에 담아서 전송합니다.
 *//*

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> ConflictException409(RuntimeException e) {

        return ResponseEntity.status(CONFLICT)
                .body(new ErrorResponseDto(CONFLICT, e.getMessage()));

    }


    */


    /**
     * 500에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
    @ExceptionHandler({NotificationDeleteFailedException.class})
    public ResponseEntity<ErrorResponseDto> internalErrorException500(RuntimeException e) {

        e.printStackTrace();

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(INTERNAL_SERVER_ERROR, e.getMessage()));
    }

}

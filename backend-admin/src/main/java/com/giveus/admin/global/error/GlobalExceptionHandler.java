package com.giveus.admin.global.error;

import com.giveus.admin.domain.funding.exception.FundingNotFoundException;
import com.giveus.admin.global.common.response.CommonResponseBody;
import com.giveus.admin.global.error.exception.InvalidRequestException;
import com.giveus.admin.global.util.ErrorMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

/**
 * rest controller 에서 예외발생시 종합적인 처리를 해주기 위한 클래스입니다.
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final ErrorMessageUtil errorMessageUtil;

    /**
     * 유효하지 않은 데이터를 전달받았을 경우를 처리하기 위한 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, NoSuchElementException.class,
            InvalidRequestException.class})
    public ResponseEntity<CommonResponseBody<String>> badRequestException400(
            MethodArgumentNotValidException e) {

        String msg = errorMessageUtil.getErrorMessage(e.getBindingResult());

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new CommonResponseBody<>(BAD_REQUEST, msg));
    }

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
    /*
//    @ExceptionHandler(value = {})
//    public ResponseEntity<CommonResponseBody<String>> forbiddenException403(RuntimeException e) {
//
//        return ResponseEntity
//                .status(FORBIDDEN)
//                .body(new CommonResponseBody<>(FORBIDDEN, e.getMessage()));
//    }

    */

    /**
     * 404에 해당하는 예외들을 한번에 처리하는 메소드입니다.
     *
     * @param e 실제 발생한 예외객체입니다.
     * @return 에러메세지를 response entity 에 담아서 전송합니다.
     */

    @ExceptionHandler({FundingNotFoundException.class})
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
     */

//    @ExceptionHandler
//    public ResponseEntity<CommonResponseBody<String>> ConflictException409(RuntimeException e) {
//
//        return ResponseEntity
//                .status(CONFLICT)
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

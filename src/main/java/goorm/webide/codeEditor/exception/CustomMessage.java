package goorm.webide.codeEditor.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomMessage {
    //200 정상처리 상태코드
    OK(HttpStatus.OK,"올바른 요청","정상적으로 처리되었습니다"),

    NO_CODE(HttpStatus.BAD_REQUEST, "잘못된 요청", "코드가 존재하지 않습니다"),

    BAD_DUPLICATE_CODE(HttpStatus.BAD_REQUEST, "잘못된 요청", "이미 코드가 존재합니다"),

    //400 잘못된 요청
    VALIDATED(HttpStatus.BAD_REQUEST,"잘못된 요청","요청한 값이 유효성검사를 통과하지 못했습니다"),

    NO_ID(HttpStatus.BAD_REQUEST,"잘못된 요청","요청한 Id가 존재하지 않습니다");

    private final HttpStatus httpStatus;
    private final String message;
    private final String detail;


    CustomMessage(HttpStatus httpStatus, String message, String detail) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.detail = detail;
    }

}

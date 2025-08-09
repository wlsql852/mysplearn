package freespring.mysplearn.domain.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //Auth
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일입니다." ),
    PASSWORD_NOT_SAME(HttpStatus.CONFLICT,"비밀번호와 비밀번호 확인이 같지 않습니다." ),
    NO_USER_EMAIL(HttpStatus.BAD_REQUEST, "해당 이메일로 가입된 유저가 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다."),

    // ### 아래 코드 위에 ErrorCode 작성해 주세요! ErrorCode 메서드 사이는 ,(컴마)로 구분해 주세요! ###
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾지못했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}

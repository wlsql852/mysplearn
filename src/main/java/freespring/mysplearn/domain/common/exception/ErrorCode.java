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
    NO_USER(HttpStatus.BAD_REQUEST, "해당하는 유저를 찾을 수 없습니다."),

    //Credit
    NO_BANK(HttpStatus.BAD_REQUEST, "해당하는 은행을 찾을 수 없습니다."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "날짜형식이 잘못되었습니다. MM/YY 형식으로 입력해주세요."),
    INVALID_CVC(HttpStatus.BAD_REQUEST, "CVC는 3자리 숫자여야 합니다."),
    NO_MAIN_CARD(HttpStatus.NOT_FOUND, "메인 카드가 없습니다."),
    NO_CREDIT(HttpStatus.BAD_REQUEST,"해당하는 카드가 없습니다." ),
    // ### 아래 코드 위에 ErrorCode 작성해 주세요! ErrorCode 메서드 사이는 ,(컴마)로 구분해 주세요! ###
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾지못했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}

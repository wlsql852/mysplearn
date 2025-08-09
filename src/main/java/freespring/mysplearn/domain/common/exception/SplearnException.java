package freespring.mysplearn.domain.common.exception;

import lombok.Getter;

@Getter
public class SplearnException extends RuntimeException {
    private ErrorCode errorCode;
    public SplearnException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

package freespring.mysplearn.domain.credit.entity;

import freespring.mysplearn.domain.common.exception.ErrorCode;
import freespring.mysplearn.domain.common.exception.SplearnException;
import lombok.Getter;

@Getter
public enum Bank {
    WOORI_BANK("우리은행"),
    KAKAO_BANK("카카오뱅크"),
    NONGHUP_BANK("농협");

    private String bankName;

    Bank(String bankName) {
        this.bankName = bankName;
    }

    public static Bank matchBank(String bankName) {
        for (Bank bank : Bank.values()) {
            if (bank.bankName.equals(bankName)) return bank;
        }
        throw new SplearnException(ErrorCode.NO_BANK);
    }
}

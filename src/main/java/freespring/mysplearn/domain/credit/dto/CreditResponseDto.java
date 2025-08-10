package freespring.mysplearn.domain.credit.dto;

import freespring.mysplearn.domain.credit.entity.Credit;
import lombok.Getter;

@Getter
public class CreditResponseDto {
    private Long creditId;
    private String nickname;
    private Boolean main;
    private String bank;
    private String cardNumber;

    public CreditResponseDto(Credit credit) {
        this.creditId = credit.getId();
        this.nickname = credit.getNickname();
        this.main = credit.getMain();
        this.bank = credit.getBank().getBankName();
        this.cardNumber = credit.getNumber().replaceAll("(?<=\\d{4})\\d", "*");
    }


}

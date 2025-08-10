package freespring.mysplearn.domain.credit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreditRequestDto {
    @NotBlank
    private String bank;

    private String nickname;

    @NotBlank
    private String number;

    @NotBlank
    private String date;

    @NotBlank
    private String cvc;


    private Boolean main;
}

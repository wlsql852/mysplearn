package freespring.mysplearn.domain.credit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreditNicknameRequestDto {
    @NotBlank
    private String nickname;
}

package freespring.mysplearn.domain.user.dto;

import lombok.Getter;

@Getter
public class UserDetailResponseDto {
    private Long id;
    private String nickname;
    private String introduction;
    private Long image;

    public UserDetailResponseDto(Long id, String nickname, String introduction, Long image) {
        this.id = id;
        this.nickname = nickname;
        this.introduction = introduction;
        this.image = image;
    }
}

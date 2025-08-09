package freespring.mysplearn.domain.user.controller;

import freespring.mysplearn.domain.auth.AuthUser;
import freespring.mysplearn.domain.user.dto.UserDetailRequestDto;
import freespring.mysplearn.domain.user.dto.UserDetailResponseDto;
import freespring.mysplearn.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //상세정보 생성
    @PostMapping("/detail/update")
    public ResponseEntity<UserDetailResponseDto> createUserDetail(@AuthenticationPrincipal AuthUser authUser,  @RequestBody @Validated UserDetailRequestDto userDetailRequestDto) {
        return userService.createUserDetail(authUser.getUserId(), userDetailRequestDto);
    }

    //나의 상세정보 조회
    @GetMapping("/detail/myintro")
    public ResponseEntity<UserDetailResponseDto> getUserDetail(@AuthenticationPrincipal AuthUser authUser) {
        return userService.getUserDetail(authUser.getUserId());
    }

    //상세정보 수정
    @PutMapping("/detail/update")
    public ResponseEntity<UserDetailResponseDto> updateUserDetail(@AuthenticationPrincipal AuthUser authUser, @RequestBody @Validated UserDetailRequestDto userDetailRequestDto) {
        return userService.updateUserDetail(authUser.getUserId(), userDetailRequestDto);
    }
}

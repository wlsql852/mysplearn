package freespring.mysplearn.domain.user.controller;

import freespring.mysplearn.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping("/signup")
//    public SignupResponseDto signup (@RequestBody SignupRequestDto signupRequestDto) {
//        // 회원가입 로직 구현
//        // 예: userService.signup(signupRequestDto);
//        return new SignupResponseDto(); // 생성된 사용자 정보 반환
//    }
}

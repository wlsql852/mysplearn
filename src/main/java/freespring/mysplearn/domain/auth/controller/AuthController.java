package freespring.mysplearn.domain.auth.controller;

import freespring.mysplearn.domain.auth.dto.SigninRequestDto;
import freespring.mysplearn.domain.auth.dto.SigninResponseDto;
import freespring.mysplearn.domain.auth.dto.SignupRequestDto;
import freespring.mysplearn.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody @Validated SignupRequestDto signupRequestDto) {
        return authService.signup(signupRequestDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signin (@RequestBody @Validated SigninRequestDto signinRequestDto) {
        return authService.signin(signinRequestDto);
    }
}

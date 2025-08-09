package freespring.mysplearn.domain.auth.service;

import freespring.mysplearn.config.JwtUtil;
import freespring.mysplearn.domain.auth.dto.SigninRequestDto;
import freespring.mysplearn.domain.auth.dto.SigninResponseDto;
import freespring.mysplearn.domain.auth.dto.SignupRequestDto;
import freespring.mysplearn.domain.common.exception.ErrorCode;
import freespring.mysplearn.domain.common.exception.SplearnException;
import freespring.mysplearn.domain.user.entity.User;
import freespring.mysplearn.domain.user.entity.UserRole;
import freespring.mysplearn.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 읽기 전용 트랜잭션 설정
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseEntity<String> signup(@Validated SignupRequestDto signupRequestDto) {
        //중복된 이메일 체크
        if(userRepository.existsUserByEmail(signupRequestDto.getEmail())) throw new SplearnException(ErrorCode.DUPLICATE_EMAIL);
        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if(signupRequestDto.getPassword() ==signupRequestDto.getConfirmPassword()) throw new SplearnException(ErrorCode.PASSWORD_NOT_SAME);

        //유저 생성
        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());
        UserRole userRole = UserRole.ROLE_USER; // 기본 사용자 역할 설정

        User user = new User(
                signupRequestDto.getEmail(),
                encodedPassword,
                userRole,
                signupRequestDto.getNickname());

        userRepository.save(user);

        return ResponseEntity.ok("회원가입 성공");
    }

    public ResponseEntity<SigninResponseDto> signin(SigninRequestDto signinRequestDto) {
        // 이메일로 유저 조회
        User user = userRepository.findByEmail(signinRequestDto.getEmail()).orElseThrow(()->new SplearnException(ErrorCode.NO_USER_EMAIL));
        //비밀번호 확인
        if(!passwordEncoder.matches(signinRequestDto.getPassword(),user.getPassword())) throw new SplearnException(ErrorCode.PASSWORD_NOT_MATCH);
        //jwt 토큰 생성
        String token = jwtUtil.createToken(user.getId(), user.getEmail(), user.getUserRole(), user.getNickname());
        //토큰 리턴
        return ResponseEntity.ok(new SigninResponseDto(token));
    }
}

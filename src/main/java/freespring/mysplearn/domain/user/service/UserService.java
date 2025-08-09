package freespring.mysplearn.domain.user.service;

import freespring.mysplearn.domain.common.exception.ErrorCode;
import freespring.mysplearn.domain.common.exception.SplearnException;
import freespring.mysplearn.domain.user.dto.UserDetailRequestDto;
import freespring.mysplearn.domain.user.dto.UserDetailResponseDto;
import freespring.mysplearn.domain.user.entity.User;
import freespring.mysplearn.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    //상세정보 생성
    @Transactional
    public ResponseEntity<UserDetailResponseDto> createUserDetail(Long userId, UserDetailRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new SplearnException(ErrorCode.NO_USER));
        user.updateDetail(requestDto.getIntroduction(), requestDto.getImage());
        userRepository.save(user);
        UserDetailResponseDto responseDto = new UserDetailResponseDto(user.getId(),  user.getNickname(), user.getIntroduction(), user.getImage());
        return ResponseEntity.ok(responseDto);
    }

    //나의 상세정보 조회
    public ResponseEntity<UserDetailResponseDto> getUserDetail(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new SplearnException(ErrorCode.NO_USER));
        UserDetailResponseDto responseDto = new UserDetailResponseDto(user.getId(), user.getNickname(), user.getIntroduction(), user.getImage());
        return ResponseEntity.ok(responseDto);
    }

    //상세정보 수정
    @Transactional
    public ResponseEntity<UserDetailResponseDto> updateUserDetail(Long userId, UserDetailRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        if(requestDto.getIntroduction() == null) user.updateImage(requestDto.getImage());
        if(requestDto.getImage() == null) user.updateIntro(requestDto.getIntroduction());
        if(requestDto.getIntroduction() != null && requestDto.getImage() != null) {
            user.updateDetail(requestDto.getIntroduction(), requestDto.getImage());
        }
        userRepository.save(user);
        UserDetailResponseDto responseDto = new UserDetailResponseDto(user.getId(), user.getNickname(), user.getIntroduction(), user.getImage());
        return ResponseEntity.ok(responseDto);
    }
}

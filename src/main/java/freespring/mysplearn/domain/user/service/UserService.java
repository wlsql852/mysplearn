package freespring.mysplearn.domain.user.service;

import freespring.mysplearn.domain.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 읽기 전용 트랜잭션 설정
public class UserService {

    private final UserRepository userRepository;
}

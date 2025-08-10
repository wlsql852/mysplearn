package freespring.mysplearn.domain.lecture.service;

import freespring.mysplearn.domain.common.exception.ErrorCode;
import freespring.mysplearn.domain.common.exception.SplearnException;
import freespring.mysplearn.domain.lecture.dto.LessonRequestDto;
import freespring.mysplearn.domain.lecture.dto.LessonResponseDto;
import freespring.mysplearn.domain.lecture.dto.LessonUpdateRequestDto;
import freespring.mysplearn.domain.lecture.entity.Lesson;
import freespring.mysplearn.domain.lecture.repository.LessonRepository;
import freespring.mysplearn.domain.user.entity.User;
import freespring.mysplearn.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;


    //강의 생성
    public ResponseEntity<LessonResponseDto> createLesson(Long userId, LessonRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(()->new SplearnException(ErrorCode.NO_USER));
        Lesson lesson = new Lesson(user, requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getThumbnail());
        Lesson newLesson = lessonRepository.save(lesson);
        LessonResponseDto responseDto = new LessonResponseDto(newLesson);
        return ResponseEntity.ok(responseDto);
    }

    //강의목록 조회
    public ResponseEntity<Page<LessonResponseDto>> getLessons(Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Pageable pageable = PageRequest.of(page, size);
        Page<LessonResponseDto> lessons = lessonRepository.findAllByInstructor(user, pageable)
                .map(LessonResponseDto::new);
        return ResponseEntity.ok(lessons);
    }

    //강의 단건 조회
    public ResponseEntity<LessonResponseDto> getLesson(Long userId, Long lessonId) {
        userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new SplearnException(ErrorCode.NO_LESSON));
        LessonResponseDto responseDto = new LessonResponseDto(lesson);
        return ResponseEntity.ok(responseDto);
    }

    //강의 수정
    public ResponseEntity<LessonResponseDto> updateLesson(Long userId, Long lessonId, LessonUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new SplearnException(ErrorCode.NO_LESSON));

        // 강의 작성자와 요청한 사용자가 일치하는지 확인
        if (!lesson.getInstructor().equals(user))  throw new SplearnException(ErrorCode.UNAUTHORIZED_USER);

        // 강의 정보 업데이트
        lesson.update(requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getThumbnail());
        lessonRepository.save(lesson);
        return ResponseEntity.ok(new LessonResponseDto(lesson));
    }

    //강의 삭제
    public ResponseEntity<String> deleteLesson(Long userId, Long lessonId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new SplearnException(ErrorCode.NO_LESSON));

        // 강의 작성자와 요청한 사용자가 일치하는지 확인
        if (!lesson.getInstructor().equals(user)) throw new SplearnException(ErrorCode.UNAUTHORIZED_USER);

        // 강의 삭제
        lessonRepository.delete(lesson);
        return ResponseEntity.ok("강의가 성공적으로 삭제되었습니다.");
    }
}

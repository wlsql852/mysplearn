package freespring.mysplearn.domain.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

//    @PostMapping
//    public LessonResponseDto createLesson(LessonRequestDto lessonRequestDto) {
//        // 강의 생성 로직 구현
//        // 예: lessonService.createLesson(lessonRequestDto);
//        return new LessonResponseDto(); // 생성된 강의 정보 반환
//    }
}

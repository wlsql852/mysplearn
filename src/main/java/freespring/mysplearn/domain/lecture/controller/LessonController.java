package freespring.mysplearn.domain.lecture.controller;

import freespring.mysplearn.domain.auth.AuthUser;
import freespring.mysplearn.domain.lecture.dto.LessonRequestDto;
import freespring.mysplearn.domain.lecture.dto.LessonResponseDto;
import freespring.mysplearn.domain.lecture.dto.LessonUpdateRequestDto;
import freespring.mysplearn.domain.lecture.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    //강의 생성
    @PostMapping("/lesson")
    public ResponseEntity<LessonResponseDto> createLesson(@AuthenticationPrincipal AuthUser authUser, @RequestBody @Validated LessonRequestDto requestDto) {
        return lessonService.createLesson(authUser.getUserId(), requestDto);
    }

    //강의 목록 조회
    @GetMapping("/lessons")
    public ResponseEntity<Page<LessonResponseDto>> getLessons(@AuthenticationPrincipal AuthUser authUser,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        return lessonService.getLessons(authUser.getUserId(), page, size);
    }

    //강의 단건 조회
     @GetMapping("/lesson/{lessonId}")
     public ResponseEntity<LessonResponseDto> getLesson(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long lessonId) {
         return lessonService.getLesson(authUser.getUserId(), lessonId);
     }

     //강의 수정
    @PutMapping("/lesson/{lessonId}")
    public ResponseEntity<LessonResponseDto> updateLesson(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long lessonId, @RequestBody LessonUpdateRequestDto requestDto) {
        return lessonService.updateLesson(authUser.getUserId(), lessonId, requestDto);
    }

    //강의삭제
    @DeleteMapping("/lesson/{lessonId}")
    public ResponseEntity<String> deleteLesson(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long lessonId) {
        return lessonService.deleteLesson(authUser.getUserId(), lessonId);
    }
}

package freespring.mysplearn.domain.lecture.dto;

import freespring.mysplearn.domain.lecture.entity.Lesson;
import lombok.Getter;

@Getter
public class LessonResponseDto {
    private Long id;
    private String title;
    private String description;
    private Integer price;

    public LessonResponseDto(Lesson newLesson) {
        this.id = newLesson.getId();
        this.title = newLesson.getTitle();
        this.description = newLesson.getDescription();
        this.price = newLesson.getPrice();
    }
}

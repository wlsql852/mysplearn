package freespring.mysplearn.domain.lecture.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LessonUpdateRequestDto {
    private String title;

    private String description;

    private Long Thumbnail;

    private Integer Price;
}

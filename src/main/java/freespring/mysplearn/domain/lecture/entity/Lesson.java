package freespring.mysplearn.domain.lecture.entity;

import freespring.mysplearn.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

//@Getter
//@Setter
//@Entity
//@Table(name="lessons")
//public class Lesson {
//
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    private Long id;
//
//    @Column(length = 100, nullable = false)
//    private String title;
//
//    private String description;
//
//    private Long Thumbnail;
//
//    @Column(nullable = false)
//    private Integer Price;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "instructor_id")
//    private User instructor; // 강의를 진행하는 강사
//}

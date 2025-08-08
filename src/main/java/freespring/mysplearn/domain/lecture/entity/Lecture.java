package freespring.mysplearn.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name="lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 300, nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer Order; // 순서

    @Column(nullable = false)
    private LocalTime time;  // 강의 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter; // 해당 강의가 속한 챕터
}

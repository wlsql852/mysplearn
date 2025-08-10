package freespring.mysplearn.domain.lecture.entity;

import freespring.mysplearn.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name="lessons")
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 300, nullable = false)
    private String description;

    private Long Thumbnail;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private User instructor; // 강의를 진행하는 강사

    public Lesson(User user, @NotBlank String title, String description, Integer price, Long thumbnail) {
        this.instructor = user;
        this.title = title;
        this.description = description;
        this.price = price;
        this.Thumbnail = thumbnail;
    }

    public void update(String title, String description, Integer price, Long thumbnail) {
        if (title != null)  this.title = title;

        if (description != null) this.description = description;

        if (price != null) this.price = price;

        if (thumbnail != null) this.Thumbnail = thumbnail;
    }
}

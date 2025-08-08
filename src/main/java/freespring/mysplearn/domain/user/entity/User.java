package freespring.mysplearn.domain.user.entity;

import freespring.mysplearn.domain.lecture.entity.Lesson;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=50, nullable = false, unique = true)
    private String Email;
    @Column(length=50, nullable = false, unique = true)
    private String Password;

    private Long Image;
    @Column(length=50, unique = true)
    private String Nickname;

    @Column(columnDefinition = "TEXT")
    private String Introduction;

    @CreatedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime RegisteredAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "instructor")
    private List<Lesson> lessons; // 강사가 진행하는 강의 목록
}

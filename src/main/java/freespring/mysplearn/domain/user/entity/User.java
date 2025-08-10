package freespring.mysplearn.domain.user.entity;

//import freespring.mysplearn.domain.lecture.entity.Lesson;
//import freespring.mysplearn.domain.credit.entity.Credit;
import freespring.mysplearn.domain.credit.entity.Credit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=50, nullable = false, unique = true)
    private String email;
    @Column(length=255, nullable = false)
    private String password;

    private Long image;

    @Column(length=50, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @CreatedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registeredAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
//
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Credit> credits;

//    @OneToMany(mappedBy = "instructor")
//    private List<Lesson> lessons; // 강사가 진행하는 강의 목록

    public User(String email, String password, UserRole role, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.userRole = role;
        this.registeredAt = LocalDateTime.now();
    }

    public void updateDetail(@NonNull String introduction, String image) {
        this.introduction = introduction;
        if (image != null && !image.isEmpty()) {
            // 이미지가 null이 아니고 비어있지 않은 경우에만 업데이트
            this.image = Long.parseLong(image);
        }
    }

    public void updateImage(String image) {
        if (image != null && !image.isEmpty()) {
            // 이미지가 null이 아니고 비어있지 않은 경우에만 업데이트
            this.image = Long.parseLong(image);
        }
    }

    public void updateIntro(@NonNull String introduction) {
        this.introduction = introduction;
    }
}

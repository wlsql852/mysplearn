package freespring.mysplearn.domain.credit.entity;

import freespring.mysplearn.domain.lecture.entity.UserLessonPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime Date; // 결제 날짜

    @Column(nullable = false)
    private Integer Amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="credit_id", nullable = false)
    private Credit credit; // 결제에 사용된 신용카드

    @Embedded
    @MapsId("userLessonPK")  //FK복합키
    private UserLessonPK userLessonPK;


}

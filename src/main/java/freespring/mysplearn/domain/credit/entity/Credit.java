package freespring.mysplearn.domain.credit.entity;

//import freespring.mysplearn.domain.user.entity.User;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@Entity
//@Table(name="credits")
//public class Credit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private Bank bank;
//
//    @Column(length = 30, nullable = false)
//    private String Number;
//
//    @Column(nullable = false)
//    private LocalDate Date;
//
//    @Column(nullable = false)
//    private String CVC;
//
//    @Column(nullable = false)
//    private Boolean Main;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//}

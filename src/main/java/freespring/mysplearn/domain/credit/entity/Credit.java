package freespring.mysplearn.domain.credit.entity;

import freespring.mysplearn.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="credits")
@NoArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nickname = "나의 카드";

    @Column(nullable = false)
    private Bank bank;

    @Column(length = 30, nullable = false)
    private String number;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String cvc;

    @Column(nullable = false)
    private Boolean main;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Credit(Bank bank, String number, String date, String cvc, Boolean main, User user) {
        this.bank = bank;
        this.number = number;
        this.date = date;
        this.cvc = cvc;
        this.main = main;
        this.user = user;
    }

    public void deleteMain() {
        this.main = false;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateMain() {
        this.main = true;
    }
}

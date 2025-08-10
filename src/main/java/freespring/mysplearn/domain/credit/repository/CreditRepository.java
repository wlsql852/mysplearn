package freespring.mysplearn.domain.credit.repository;

import freespring.mysplearn.domain.credit.entity.Credit;
import freespring.mysplearn.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository  extends JpaRepository<Credit,Long> {

    Credit findByUserAndMain(User user, boolean b);

    boolean existsByUserAndMain(User user, boolean b);

    Page<Credit> findAllByUser(User user, Pageable pageable);
}

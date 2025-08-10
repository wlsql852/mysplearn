package freespring.mysplearn.domain.lecture.repository;

import freespring.mysplearn.domain.lecture.entity.Lesson;
import freespring.mysplearn.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Page<Lesson> findAllByInstructor(User user, Pageable pageable);
}

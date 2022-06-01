package com.github.eeriefoods.snsserver.course.repository;

import com.github.eeriefoods.snsserver.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}

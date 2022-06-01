package com.github.eeriefoods.snsserver.student.repository;

import com.github.eeriefoods.snsserver.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

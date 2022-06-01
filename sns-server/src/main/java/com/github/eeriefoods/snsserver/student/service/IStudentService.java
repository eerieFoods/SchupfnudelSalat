package com.github.eeriefoods.snsserver.student.service;

import com.github.eeriefoods.snsserver.student.domain.Student;

public interface IStudentService {

    Student getStudent(Long studentId);

    Student createStudent(Student student);

    Student updateStudent(Student newStudent);

    void exmatriculate(Long studentId);

}

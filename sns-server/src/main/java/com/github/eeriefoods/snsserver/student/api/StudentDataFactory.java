package com.github.eeriefoods.snsserver.student.api;

import com.github.eeriefoods.snsserver.student.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDataFactory {

    public StudentData from(Student student) {
        StudentData studentData = new StudentData();

        studentData.setStudentId(student.getStudentId());
        studentData.setCompany(student.getCompany());
        studentData.setFirstName(student.getFirstName());
        studentData.setLastName(student.getLastName());
        studentData.setCourseId(student.getCourse().getId());
        studentData.setJavaLevel(student.getJavaLevel());

        return studentData;
    }

}

package com.github.eeriefoods.snsserver.student.api;

import com.github.eeriefoods.snsserver.student.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDataFactory {

    public StudentData from(Student student) {
        StudentData studentData = new StudentData();

        studentData.setMatrikelNummer(studentData.getMatrikelNummer());
        studentData.setCompany(student.getCompany());
        studentData.setFirstName(student.getFirstName());
        studentData.setLastName(studentData.getLastName());
        studentData.setKursId(student.getKurs().getId());

        return studentData;
    }

}

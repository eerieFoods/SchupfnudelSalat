package com.github.eeriefoods.snsserver.student.domain;

import com.github.eeriefoods.snsserver.kurs.service.KursService;
import com.github.eeriefoods.snsserver.student.api.StudentData;
import org.springframework.stereotype.Component;

@Component
public class StudentFactory {

    private final KursService kursService;

    public StudentFactory(KursService kursService) {
        this.kursService = kursService;
    }

    public Student from(StudentData studentData) {
        Student student = new Student();

        student.setMatrikelNummer(studentData.getMatrikelNummer());
        student.setKurs(kursService.getKurs(studentData.getKursId()));
        student.setJavaStand(studentData.getJavaStand());
        student.setCompany(studentData.getCompany());
        student.setFirstName(studentData.getFirstName());
        student.setLastName(studentData.getLastName());

        return student;
    }

}

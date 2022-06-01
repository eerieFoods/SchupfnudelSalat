package com.github.eeriefoods.snsserver.kurs.service;

import com.github.eeriefoods.snsserver.kurs.domain.Kurs;
import com.github.eeriefoods.snsserver.student.domain.Student;

public interface IKursService {

    Kurs getKurs(String kursId);

    Kurs createKurs(Kurs kurs);

    Kurs updateKurs(Kurs kurs);

    Kurs addStudent(String kursId, Student student);

    Kurs removeStudent(String kursId, Student student);

    void deleteKurs(String kursId);

}

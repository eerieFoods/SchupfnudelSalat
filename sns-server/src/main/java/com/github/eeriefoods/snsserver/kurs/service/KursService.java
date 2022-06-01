package com.github.eeriefoods.snsserver.kurs.service;

import com.github.eeriefoods.snsserver.kurs.api.KursNotFoundException;
import com.github.eeriefoods.snsserver.kurs.api.StudentNotMemberOfKursException;
import com.github.eeriefoods.snsserver.kurs.domain.Kurs;
import com.github.eeriefoods.snsserver.kurs.repository.KursRepository;
import com.github.eeriefoods.snsserver.student.domain.Student;
import com.github.eeriefoods.snsserver.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class KursService implements IKursService {

    private final KursRepository kursRepository;
    private final StudentRepository studentRepository;

    public KursService(KursRepository kursRepository, StudentRepository studentRepository) {
        this.kursRepository = kursRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Kurs getKurs(String kursId) {
        return kursRepository.findById(kursId)
                .orElseThrow(() -> new KursNotFoundException(kursId));
    }

    @Override
    public Kurs createKurs(Kurs kurs) {
        return kursRepository.save(kurs);
    }

    @Override
    public Kurs updateKurs(Kurs newKurs) {
        Kurs kurs = kursRepository.findById(newKurs.getId())
                .orElseThrow(() -> new KursNotFoundException(newKurs.getId()));

        kurs.setRoom(newKurs.getRoom());
        kurs.setMembers(newKurs.getMembers());

        return kursRepository.save(newKurs);
    }

    @Override
    public Kurs addStudent(String kursId, Student student) {
        Kurs kurs = kursRepository.findById(kursId)
                .orElseThrow(() -> new KursNotFoundException(kursId));

        if (!studentRepository.existsById(student.getMatrikelNummer())) {
            studentRepository.save(student);
        }

        kurs.getMembers().add(student);

        return updateKurs(kurs);
    }

    @Override
    public Kurs removeStudent(String kursId, Student student) {
        Kurs kurs = kursRepository.findById(kursId)
                .orElseThrow(() -> new KursNotFoundException(kursId));

        if (!kurs.getMembers().stream().map(Student::getMatrikelNummer).toList().contains(student.getMatrikelNummer())) {
            throw new StudentNotMemberOfKursException(kursId, student.getMatrikelNummer());
        }

        kurs.setMembers(kurs.getMembers().stream()
                .filter(s -> !Objects.equals(s.getMatrikelNummer(), student.getMatrikelNummer()))
                .collect(Collectors.toSet()));

        return updateKurs(kurs);
    }

    @Override
    public void deleteKurs(String kursId) {
        if (!kursRepository.existsById(kursId))
            throw new KursNotFoundException(kursId);
        kursRepository.deleteById(kursId);
    }

}

package com.github.eeriefoods.snsserver.student.service;

import com.github.eeriefoods.snsserver.student.api.StudentNotFoundException;
import com.github.eeriefoods.snsserver.student.domain.Student;
import com.github.eeriefoods.snsserver.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student newStudent) {
        Student student = studentRepository.findById(newStudent.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(newStudent.getStudentId()));

        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setCompany(newStudent.getCompany());
        student.setCourse(newStudent.getCourse());
        student.setJavaLevel(newStudent.getJavaLevel());

        return studentRepository.save(student);
    }

    @Override
    public void exmatriculate(Long studentId) {
        if (!studentRepository.existsById(studentId))
            throw new StudentNotFoundException(studentId);

        studentRepository.deleteById(studentId);
    }


}

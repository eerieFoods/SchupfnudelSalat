package com.github.eeriefoods.snsserver.student.service;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.student.api.StudentNotFoundException;
import com.github.eeriefoods.snsserver.student.domain.Student;
import com.github.eeriefoods.snsserver.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StudentServiceTest extends BaseMockitoTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private Student student;
    @InjectMocks
    private StudentService studentService;

    @Test
    void testGetStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.getStudent(123L);

        verify(studentRepository).findById(anyLong());
    }

    @Test
    void testGetStudent_NonExistingId() {
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudent(123L));
    }

    @Test
    void testCreateStudent() {
        studentService.createStudent(student);

        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void testUpdateStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.updateStudent(student);

        verify(studentRepository).findById(anyLong());
        verify(studentRepository).save(any(Student.class));
        // Never change the StudentId!
        verify(student, never()).setStudentId(anyLong());
    }

    @Test
    void testUpdateStudent_NonExistingId() {
        assertThrows(StudentNotFoundException.class, () -> studentService.updateStudent(student));
    }

    @Test
    void testExmatriculate() {
        when(studentRepository.existsById(anyLong())).thenReturn(true);

        studentService.exmatriculate(123L);

        verify(studentRepository).deleteById(anyLong());
    }

    @Test
    void testExmatriculate_NonExistingId() {
        assertThrows(StudentNotFoundException.class, () -> studentService.exmatriculate(123L));
    }

}
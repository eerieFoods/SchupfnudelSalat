package com.github.eeriefoods.snsserver.student.api;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.student.domain.Student;
import com.github.eeriefoods.snsserver.student.domain.StudentFactory;
import com.github.eeriefoods.snsserver.student.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

class StudentControllerTest extends BaseMockitoTest {

    @Mock
    private IStudentService studentService;
    @Mock
    private StudentDataFactory studentDataFactory;
    @Mock
    private StudentFactory studentFactory;
    @Mock
    private StudentData studentData;
    @InjectMocks
    private StudentController studentController;

    @Test
    void testGetStudent() {
        studentController.getStudent(anyLong());

        verify(studentService).getStudent(anyLong());
    }

    @Test
    void testCreateStudent() {
        studentController.createStudent(studentData);

        verify(studentService).createStudent(any());
        verify(studentFactory).from(any());
    }

    @Test
    void testUpdateStudent() {
        studentController.updateStudent(studentData);

        verify(studentService).updateStudent(any());
        verify(studentFactory).from(any());
    }

    @Test
    void testExmatriculate() {
        studentController.exmatriculate(123L);

        verify(studentService).exmatriculate(anyLong());
    }
}
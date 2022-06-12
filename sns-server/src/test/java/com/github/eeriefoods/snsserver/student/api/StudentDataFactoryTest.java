package com.github.eeriefoods.snsserver.student.api;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.course.domain.Course;
import com.github.eeriefoods.snsserver.student.domain.JavaLevel;
import com.github.eeriefoods.snsserver.student.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentDataFactoryTest extends BaseMockitoTest {

    @Mock
    private Student expected;
    @Mock
    private Course course;
    @InjectMocks
    private StudentDataFactory studentDataFactory;

    @Test
    void testFrom() {
        when(expected.getStudentId()).thenReturn(123456L);
        when(expected.getJavaLevel()).thenReturn(JavaLevel.GUT);
        when(expected.getCompany()).thenReturn("DHBW");
        when(expected.getFirstName()).thenReturn("Max");
        when(expected.getLastName()).thenReturn("Mustermann");
        when(expected.getCourse()).thenReturn(course);
        when(course.getId()).thenReturn("CourseID");

        StudentData actual = studentDataFactory.from(expected);

        assertEquals(expected.getStudentId(), actual.getStudentId());
        assertEquals(expected.getJavaLevel(), actual.getJavaLevel());
        assertEquals(expected.getCompany(), actual.getCompany());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getCourse().getId(), actual.getCourseId());
    }
}
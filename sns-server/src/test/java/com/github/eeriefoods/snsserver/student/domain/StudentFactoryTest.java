package com.github.eeriefoods.snsserver.student.domain;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.course.domain.Course;
import com.github.eeriefoods.snsserver.course.service.CourseService;
import com.github.eeriefoods.snsserver.student.api.StudentData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class StudentFactoryTest extends BaseMockitoTest {

    @Mock
    private CourseService courseService;
    @Mock
    private Course course;
    @InjectMocks
    private StudentFactory studentFactory;

    @Test
    void testFrom() {
        when(courseService.getCourse(anyString())).thenReturn(course);

        StudentData expected = new StudentData();
        expected.setStudentId(123456L);
        expected.setJavaLevel(JavaLevel.AUSREICHEND);
        expected.setLastName("Mustermann");
        expected.setFirstName("Max");
        expected.setCourseId("CourseId");

        Student actual = studentFactory.from(expected);

        assertEquals(expected.getStudentId(), actual.getStudentId());
        assertEquals(expected.getJavaLevel(), actual.getJavaLevel());
        assertEquals(expected.getCompany(), actual.getCompany());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }
}
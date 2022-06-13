package com.github.eeriefoods.snsserver.student.api;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.course.domain.Course;
import com.github.eeriefoods.snsserver.student.domain.JavaLevel;
import com.github.eeriefoods.snsserver.student.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    void testFromList() {
        when(expected.getStudentId()).thenReturn(123456L);
        when(expected.getJavaLevel()).thenReturn(JavaLevel.GUT);
        when(expected.getCompany()).thenReturn("DHBW");
        when(expected.getFirstName()).thenReturn("Max");
        when(expected.getLastName()).thenReturn("Mustermann");
        when(expected.getCourse()).thenReturn(course);
        when(course.getId()).thenReturn("CourseID");

        List<Student> expectedList = List.of(expected);

        List<StudentData> actual = studentDataFactory.from(expectedList);

        IntStream.range(0, expectedList.size()).forEach(index -> {
            assertEquals(expectedList.get(index).getStudentId(), actual.get(index).getStudentId());
            assertEquals(expectedList.get(index).getJavaLevel(), actual.get(index).getJavaLevel());
            assertEquals(expectedList.get(index).getCompany(), actual.get(index).getCompany());
            assertEquals(expectedList.get(index).getFirstName(), actual.get(index).getFirstName());
            assertEquals(expectedList.get(index).getLastName(), actual.get(index).getLastName());
            assertEquals(expectedList.get(index).getCourse().getId(), actual.get(index).getCourseId());
        });

        assertEquals(expectedList.size(), actual.size());
        assertFalse(actual.isEmpty());
    }
}
package com.github.eeriefoods.snsserver.course.api;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.course.domain.Course;
import com.github.eeriefoods.snsserver.course.domain.CourseFactory;
import com.github.eeriefoods.snsserver.course.service.CourseService;
import com.github.eeriefoods.snsserver.student.api.StudentData;
import com.github.eeriefoods.snsserver.student.domain.StudentFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

class CourseControllerTest extends BaseMockitoTest {

    @Mock
    private CourseService courseService;
    @Mock
    private CourseDataFactory courseDataFactory;
    @Mock
    private CourseFactory courseFactory;
    @Mock
    private StudentFactory studentFactory;
    @Mock
    private Course course;
    @InjectMocks
    private CourseController courseController;

    @Test
    void testGetCourse() {
        courseController.getCourse("MyCourse");

        verify(courseService).getCourse(anyString());
        verify(courseDataFactory).from(any());
    }

    @Test
    void testGetAllCourses() {
        courseController.getAllCourses();

        verify(courseService).getAllCourses();
    }

    @Test
    void testCreateCourse() {
        courseController.createCourse(new CourseData());

        verify(courseService).createCourse(any());
        verify(courseDataFactory).from(any());
        verify(courseFactory).from(any());
    }

    @Test
    void testUpdateCourse() {
        courseController.updateCourse(new CourseData());

        verify(courseService).updateCourse(any());
        verify(courseFactory).from(any());
        verify(courseDataFactory).from(any());
    }

    @Test
    void testAddMemberToCourse() {
        courseController.addMemberToCourse("MyCourse", new StudentData());

        verify(courseService).addStudent(anyString(), any());
        verify(courseDataFactory).from(any());
        verify(studentFactory).from(any());
    }

    @Test
    void testRemoveMemberFromCourse() {
        courseController.removeMemberFromCourse("MyCourse", new StudentData());

        verify(courseService).removeStudent(anyString(), any());
        verify(courseDataFactory).from(any());
        verify(studentFactory).from(any());
    }

    @Test
    void testDeleteCourse() {
        courseController.deleteCourse("MyCourse");

        verify(courseService).deleteCourse(anyString());
    }
}
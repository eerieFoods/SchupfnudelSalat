package com.github.eeriefoods.snsserver.course.service;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.course.api.CourseNotFoundException;
import com.github.eeriefoods.snsserver.course.domain.Course;
import com.github.eeriefoods.snsserver.course.repository.CourseRepository;
import com.github.eeriefoods.snsserver.student.domain.Student;
import com.github.eeriefoods.snsserver.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CourseServiceTest extends BaseMockitoTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private Course course;
    @Mock
    private Student student;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private CourseService courseService;

    @Test
    void testGetCourse() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.of(course));

        courseService.getCourse("CourseId");

        verify(courseRepository).findById(anyString());
    }

    @Test
    void testGetCourse_NonExistingId() {
        assertThrows(CourseNotFoundException.class, () -> courseService.getCourse("NonExistingId"));
    }

    @Test
    void testCreateCourse() {
        courseService.createCourse(course);

        verify(courseRepository).save(any(Course.class));
    }

    @Test
    void testUpdateCourse() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.of(course));
        when(course.getId()).thenReturn("CourseId");

        courseService.updateCourse(course);

        verify(courseRepository).findById(anyString());
        verify(courseRepository).save(any(Course.class));
        // Never Change CourseId!
        verify(course, never()).setId(anyString());
    }

    @Test
    void testUpdateCourse_NonExistingId() {
        assertThrows(CourseNotFoundException.class, () -> courseService.updateCourse(course));
    }

    @Test
    void testDeleteCourse() {
        when(courseRepository.existsById(anyString())).thenReturn(true);

        courseService.deleteCourse("CourseId");

        verify(courseRepository).deleteById(anyString());
    }

    @Test
    void testDeleteCourse_NonExistingId() {
        assertThrows(CourseNotFoundException.class, () -> courseService.deleteCourse("NonExistingCourse"));
    }

    @Test
    void testGetAllCourses() {
        courseService.getAllCourses();

        verify(courseRepository).findAll();
    }
}
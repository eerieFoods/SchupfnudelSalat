package com.github.eeriefoods.snsserver.course.service;

import com.github.eeriefoods.snsserver.course.api.CourseAlreadyExistsException;
import com.github.eeriefoods.snsserver.course.api.CourseNotFoundException;
import com.github.eeriefoods.snsserver.course.api.StudentNotMemberOfCourseException;
import com.github.eeriefoods.snsserver.course.domain.Course;
import com.github.eeriefoods.snsserver.course.repository.CourseRepository;
import com.github.eeriefoods.snsserver.student.domain.Student;
import com.github.eeriefoods.snsserver.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Course getCourse(String courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    @Override
    public Course createCourse(Course course) {
        if (courseRepository.existsById(course.getId())) {
            throw new CourseAlreadyExistsException(course.getId());
        }
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course newCourse) {
        Course course = courseRepository.findById(newCourse.getId())
                .orElseThrow(() -> new CourseNotFoundException(newCourse.getId()));

        course.setRoom(newCourse.getRoom());
        course.setMembers(newCourse.getMembers());

        return courseRepository.save(newCourse);
    }

    @Override
    public Course addStudent(String courseId, Student student) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        if (!studentRepository.existsById(student.getStudentId())) {
            studentRepository.save(student);
        }

        course.getMembers().add(student);

        return updateCourse(course);
    }

    @Override
    public Course removeStudent(String courseId, Student student) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        if (!course.getMembers().stream().map(Student::getStudentId).toList().contains(student.getStudentId())) {
            throw new StudentNotMemberOfCourseException(courseId, student.getStudentId());
        }

        course.setMembers(course.getMembers().stream()
                .filter(s -> !Objects.equals(s.getStudentId(), student.getStudentId()))
                .collect(Collectors.toSet()));

        return updateCourse(course);
    }

    @Override
    public void deleteCourse(String courseId) {
        if (!courseRepository.existsById(courseId))
            throw new CourseNotFoundException(courseId);
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}

package com.github.eeriefoods.snsserver.student.domain;

import com.github.eeriefoods.snsserver.course.service.CourseService;
import com.github.eeriefoods.snsserver.student.api.StudentData;
import org.springframework.stereotype.Component;

@Component
public class StudentFactory {

    private final CourseService courseService;

    public StudentFactory(CourseService courseService) {
        this.courseService = courseService;
    }

    public Student from(StudentData studentData) {
        Student student = new Student();

        student.setStudentId(studentData.getStudentId());
        student.setCourse(courseService.getCourse(studentData.getCourseId()));
        student.setJavaLevel(studentData.getJavaLevel());
        student.setCompany(studentData.getCompany());
        student.setFirstName(studentData.getFirstName());
        student.setLastName(studentData.getLastName());

        return student;
    }

}

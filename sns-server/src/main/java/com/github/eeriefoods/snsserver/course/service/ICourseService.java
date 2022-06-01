package com.github.eeriefoods.snsserver.course.service;

import com.github.eeriefoods.snsserver.course.domain.Course;
import com.github.eeriefoods.snsserver.student.domain.Student;

public interface ICourseService {

    Course getCourse(String courseId);

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Course addStudent(String courseId, Student student);

    Course removeStudent(String courseId, Student student);

    void deleteCourse(String courseId);

}

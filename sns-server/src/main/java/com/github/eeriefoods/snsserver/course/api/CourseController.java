package com.github.eeriefoods.snsserver.course.api;

import com.github.eeriefoods.snsserver.course.domain.CourseFactory;
import com.github.eeriefoods.snsserver.course.service.ICourseService;
import com.github.eeriefoods.snsserver.student.api.StudentData;
import com.github.eeriefoods.snsserver.student.domain.StudentFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "course")
public class CourseController {

    private final CourseDataFactory courseDataFactory;
    private final ICourseService courseService;
    private final CourseFactory courseFactory;
    private final StudentFactory studentFactory;

    public CourseController(CourseDataFactory courseDataFactory, ICourseService courseService, CourseFactory courseFactory, StudentFactory studentFactory) {
        this.courseDataFactory = courseDataFactory;
        this.courseService = courseService;
        this.courseFactory = courseFactory;
        this.studentFactory = studentFactory;
    }

    @GetMapping(path = "{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseData getCourse(@PathVariable String courseId) {
        return courseDataFactory.from(courseService.getCourse(courseId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseData createCourse(@Valid @RequestBody CourseData courseData) {
        return courseDataFactory.from(courseService.createCourse(courseFactory.from(courseData)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseData updateCourse(@Valid @RequestBody CourseData courseData) {
        return courseDataFactory.from(courseService.updateCourse(courseFactory.from(courseData)));
    }

    @PutMapping(path = "{courseId}/add",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseData addMemberToCourse(@PathVariable String courseId, @Valid @RequestBody StudentData studentData) {
        return courseDataFactory.from(courseService.addStudent(courseId, studentFactory.from(studentData)));
    }

    @DeleteMapping(path = "{courseId}/remove",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseData removeMemberFromCourse(@PathVariable String courseId, @Valid @RequestBody StudentData studentData) {
        return courseDataFactory.from(courseService.removeStudent(courseId, studentFactory.from(studentData)));
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
    }

}

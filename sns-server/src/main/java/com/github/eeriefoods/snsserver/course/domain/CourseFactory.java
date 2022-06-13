package com.github.eeriefoods.snsserver.course.domain;

import com.github.eeriefoods.snsserver.course.api.CourseData;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

@Component
public class CourseFactory {

    public Course from(CourseData courseData) {
        Course course = new Course();

        course.setId(courseData.getId());
        course.setMembers(Optional.ofNullable(courseData.getMembers()).orElse(new HashSet<>()));
        course.setFriendlyName(courseData.getFriendlyMame());
        course.setRoom(courseData.getRoom());

        return course;
    }

}

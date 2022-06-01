package com.github.eeriefoods.snsserver.course.api;

import com.github.eeriefoods.snsserver.course.domain.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseDataFactory {

    public CourseData from(Course course) {
        CourseData data = new CourseData();

        data.setId(course.getId());
        data.setMembers(course.getMembers());
        data.setRoom(course.getRoom());

        return data;
    }

}

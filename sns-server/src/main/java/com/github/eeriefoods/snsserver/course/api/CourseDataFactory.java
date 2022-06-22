package com.github.eeriefoods.snsserver.course.api;

import com.github.eeriefoods.snsserver.course.domain.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDataFactory {

    public CourseData from(Course course) {
        CourseData data = new CourseData();

        data.setId(course.getId());
        data.setMembers(course.getMembers());
        data.setMemberCount((short) course.getMembers().size());
        data.setRoom(course.getRoom());

        return data;
    }

    public List<CourseData> from(List<Course> courses) {
        return courses.stream()
                .map(this::from)
                .toList();
    }

}

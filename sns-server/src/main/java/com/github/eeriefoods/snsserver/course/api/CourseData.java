package com.github.eeriefoods.snsserver.course.api;

import com.github.eeriefoods.snsserver.student.domain.Student;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class CourseData {

    @NotBlank
    private String id;

    @NotBlank
    private String room;

    private String friendlyMame;

    private Set<Student> members;

}

package com.github.eeriefoods.snsserver.student.api;

import com.github.eeriefoods.snsserver.student.domain.JavaLevel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class StudentData {

    @Positive
    private Long studentId;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    private JavaLevel javaLevel;

    @NotBlank
    private String courseId;

    private String company;

}

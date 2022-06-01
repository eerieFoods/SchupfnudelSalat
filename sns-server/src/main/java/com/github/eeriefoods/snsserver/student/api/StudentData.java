package com.github.eeriefoods.snsserver.student.api;

import com.github.eeriefoods.snsserver.student.domain.JavaStand;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class StudentData {

    @NotNull
    @Positive
    private Long matrikelNummer;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    private JavaStand javaStand;

    @NotBlank
    private String kursId;

    private String company;

}

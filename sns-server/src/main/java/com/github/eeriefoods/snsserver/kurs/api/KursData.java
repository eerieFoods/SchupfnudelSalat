package com.github.eeriefoods.snsserver.kurs.api;

import com.github.eeriefoods.snsserver.student.domain.Student;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class KursData {

    @NotBlank
    private String id;

    @NotBlank
    private String room;

    private Set<Student> members;

}

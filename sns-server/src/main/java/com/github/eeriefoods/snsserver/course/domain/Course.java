package com.github.eeriefoods.snsserver.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.eeriefoods.snsserver.student.domain.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SNS_COURSE")
@Getter
@Setter
@ToString
public class Course {

    @Id
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private String id;

    @Column(name = "FRIENDLY_NAME")
    private String friendlyName;

    @Column(name = "ROOM", nullable = false)
    private String room;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SNS_COURSE_MEMBERS")
    @Column(name = "MEMBERS")
    @JsonIgnore // Prevents infinite recursion
    private Set<Student> members = new HashSet<>();

}

package com.github.eeriefoods.snsserver.student.domain;

import com.github.eeriefoods.snsserver.course.domain.Course;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "SNS_STUDENT")
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long studentId;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(name = "JAVA_LEVEL")
    private JavaLevel javaLevel;

    @OneToOne
    @JoinColumn(name = "COURSE_ID")
    @ToString.Exclude
    private Course course;

    @Column(name = "COMPANY")
    private String company;


}

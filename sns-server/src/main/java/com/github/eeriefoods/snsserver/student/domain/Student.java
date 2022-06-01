package com.github.eeriefoods.snsserver.student.domain;

import com.github.eeriefoods.snsserver.kurs.domain.Kurs;
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
    private Long matrikelNummer;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(name = "JAVA_STAND")
    private JavaStand javaStand;

    @OneToOne
    @JoinColumn(name = "KURS_ID")
    @ToString.Exclude
    private Kurs kurs;

    @Column(name = "COMPANY")
    private String company;


}

package com.github.eeriefoods.snsserver.kurs.domain;

import com.github.eeriefoods.snsserver.student.domain.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SNS_KURS")
@Getter
@Setter
@ToString
public class Kurs {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "ROOM", nullable = false)
    private String room;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SNS_KURS_MEMBERS")
    @Column(name = "MEMBERS")
    private Set<Student> members = new HashSet<>();

}

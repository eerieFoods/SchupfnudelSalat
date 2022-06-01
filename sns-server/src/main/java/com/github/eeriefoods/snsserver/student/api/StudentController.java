package com.github.eeriefoods.snsserver.student.api;

import com.github.eeriefoods.snsserver.student.domain.StudentFactory;
import com.github.eeriefoods.snsserver.student.service.IStudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "student")
public class StudentController {

    private final IStudentService studentService;
    private final StudentDataFactory studentDataFactory;
    private final StudentFactory studentFactory;

    public StudentController(IStudentService studentService, StudentDataFactory studentDataFactory, StudentFactory studentFactory) {
        this.studentService = studentService;
        this.studentDataFactory = studentDataFactory;
        this.studentFactory = studentFactory;
    }

    @GetMapping(path = "{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentData getStudent(@PathVariable Long studentId) {
        return studentDataFactory.from(studentService.getStudent(studentId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentData createStudent(@Valid @RequestBody StudentData studentData) {
        return studentDataFactory.from(studentService.createStudent(studentFactory.from(studentData)));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentData updateStudent(@Valid @RequestBody StudentData studentData) {
        return studentDataFactory.from(studentService.updateStudent(studentFactory.from(studentData)));
    }

    @DeleteMapping(path = "{studentId}")
    public void exmatriculate(@PathVariable Long studentId) {
        studentService.exmatriculate(studentId);
    }

}

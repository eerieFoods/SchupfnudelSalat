package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.List;


public class StudentController {
    @FXML private TableView<Student> TCS_View;
    @FXML private TableColumn<Student, Integer> TCS_ID;
    @FXML private TableColumn<Student, String> TCS_FirstName, TCS_LastName, TCS_Company;
    @FXML private TableColumn<Student, JavaLevel> TCS_JavaLevel;
    @FXML private TableColumn<Student, String> TCS_Course;

    @FXML private void initialize() {

        TCS_ID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        TCS_FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TCS_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TCS_LastName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_JavaLevel.setCellValueFactory(new PropertyValueFactory<>("javaLevel"));
        TCS_JavaLevel.setCellFactory(ComboBoxTableCell.forTableColumn(JavaLevel.values()));
        TCS_Company.setCellValueFactory(new PropertyValueFactory<>("company"));
        TCS_Company.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_Course.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        try { // TODO Error Handling
            String[] courses = CourseService
                    .getCourses()
                    .stream()
                    .map(Course::getFriendlyName)
                    .toArray(String[]::new);

            TCS_Course.setCellFactory(ComboBoxTableCell.forTableColumn(courses));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        TCS_View.getItems().setAll(loadStudentList());
        TCS_FirstName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(event.getNewValue());
            updateStudent(event.getRowValue());
        });
        TCS_LastName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(event.getNewValue());
            updateStudent(event.getRowValue());
        });
        TCS_Company.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCompany(event.getNewValue());
            updateStudent(event.getRowValue());
        });
        TCS_Course.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCourse(event.getNewValue());
            updateStudent(event.getRowValue());
        });
        TCS_JavaLevel.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setJavaLevel(event.getNewValue());
            updateStudent(event.getRowValue());
        });

        TCS_View.setEditable(true);
    }

    private void updateStudent(Student student){
        try {
            StudentService.updateStudent(student);
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<Student> loadStudentList(){
        try {
            return StudentService.getAllStudents();
        } catch (IOException | InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}

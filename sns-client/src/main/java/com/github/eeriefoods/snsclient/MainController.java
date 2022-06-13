package com.github.eeriefoods.snsclient;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

import com.github.eeriefoods.snsclient.model.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML private TableView<Student> TCS_View;
    @FXML private TableColumn<Student, Integer> TCS_ID;
    @FXML private TableColumn<Student, String> TCS_FirstName;
    @FXML private TableColumn<Student, String> TCS_LastName;
    @FXML private TableColumn<Student, String> TCS_Course;
    @FXML private TableColumn<Student, String> TCS_Company;
    @FXML private TableColumn<Student, JavaLevel> TCS_JavaLevel;

    @FXML private void initialize() {
        
        TCS_ID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        TCS_FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TCS_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TCS_JavaLevel.setCellValueFactory(new PropertyValueFactory<>("javaLevel"));
        TCS_Company.setCellValueFactory(new PropertyValueFactory<>("company"));
        TCS_Course.setCellValueFactory(new PropertyValueFactory<>("course"));
        TCS_View.getItems().setAll(parseStudentList());
    }

    private List<Student> parseStudentList() {
        Course Test = new Course("TINF21AI2","TINF21AI22", "123B");
        List<Student> Students = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Student iStudent = new Student( 0+i, "ASDF", "WASD", JavaLevel.SEHR_GUT, i + " NONAMECOMPANY", Test);
            Students.add(iStudent);
        }
        return Students;
    }
}
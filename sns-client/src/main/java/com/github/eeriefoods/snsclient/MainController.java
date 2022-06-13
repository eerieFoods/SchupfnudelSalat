package com.github.eeriefoods.snsclient;

import com.github.eeriefoods.snsclient.service.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
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
        TCS_Course.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        TCS_View.getItems().setAll(parseStudentList());
    }

    private List<Student> parseStudentList() {
        try {
            return StudentService.getAllStudents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
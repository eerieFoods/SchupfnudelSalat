package com.github.eeriefoods.snsclient.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddBarController {
    @FXML public AnchorPane addBar;
    @FXML private Button BTNSave;
    @FXML private Button BTNCancel;
    @FXML private TextField TFDStudentId;
    @FXML private TextField TFDFirstName;
    @FXML private TextField TFDLastName;
    @FXML private ComboBox CBXJavaLevel;
    @FXML private ComboBox CBXCourse;
    @FXML private TextField TFDCompany;

    private StudentController studentController;
    private CourseController courseController;

    @FXML public void initialize() throws IOException {





    }

    public void injectStudentController(StudentController studentController){
        this.studentController = studentController;
    }
    public void injectCourseController(CourseController courseController){
        this.courseController = courseController;
    }


}

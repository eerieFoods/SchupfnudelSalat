package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StudentAddBarController {
    @FXML public AnchorPane addBar;
    @FXML private Button BTNSave;
    @FXML private Button BTNCancel;
    @FXML private TextField TFDStudentId;
    @FXML private TextField TFDFirstName;
    @FXML private TextField TFDLastName;
    @FXML private ComboBox<JavaLevel> CBXJavaLevel;
    @FXML private ComboBox<Course> CBXCourse;
    @FXML private TextField TFDCompany;
    private MainController mainController;
    private StudentTableController studentTableController;

    private CourseTableController courseController;
    private TableView<Student> studentTableView;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
        this.studentTableController = mainController.studentTableController;
        this.studentTableView = studentTableController.TCS_View;
    }



    @FXML public void initialize() throws IOException, InterruptedException {

        initComboBoxes();

        BTNCancel.setOnAction(event -> {
            mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
        });

        BTNSave.setOnAction(event -> {
            Student student = new Student(null, TFDFirstName.getText(), TFDLastName.getText(), CBXJavaLevel.getValue(), TFDCompany.getText(), CBXCourse.getValue().getId());
            try {
                StudentService.createStudent(student);
                studentTableView.getItems().setAll(studentTableController.loadStudentList());
                mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initComboBoxes() throws IOException, InterruptedException {
        CBXJavaLevel.setItems(FXCollections.observableArrayList(JavaLevel.values()));
        CBXCourse.setItems(FXCollections.observableArrayList(CourseService.getCourses()));
    }

}

package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StudentAddBarController {
    @FXML private Button BTNSave;
    @FXML private Button BTNCancel;
    //@FXML private TextField TFDStudentId;
    @FXML private TextField TFDFirstName;
    @FXML private TextField TFDLastName;
    @FXML private ComboBox<JavaLevel> CBXJavaLevel;
    @FXML private ComboBox<Course> CBXCourse;
    @FXML private TextField TFDCompany;
    private MainController mainController;
    private StudentTableController studentTableController;
    private TableView<Student> studentTableView;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
        this.studentTableController = mainController.studentTableController;
        this.studentTableView = studentTableController.TCS_View;
    }

    @FXML public void initialize() throws IOException, InterruptedException {

        initComboBoxes();

        BTNCancel.setOnAction(event -> {

            resetCBX();
            mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
                });



        BTNSave.setOnAction(event -> {
            if (!TFDFirstName.getText().isEmpty() && !TFDLastName.getText().isEmpty() && !CBXJavaLevel.getSelectionModel().isEmpty() && !CBXCourse.getSelectionModel().isEmpty() &&!TFDCompany.getText().isEmpty())
            {
                Student student = new Student(null, TFDFirstName.getText(), TFDLastName.getText(), CBXJavaLevel.getValue(), TFDCompany.getText(), CBXCourse.getValue());
                try {
                    StudentService.createStudent(student);
                    studentTableView.getItems().setAll(studentTableController.loadStudentList());
                    resetCBX();
                    mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
                    NotificationHandler.showUserNotification("Student:in erfolgreich angelegt!", "Der Student/die Studentin " + TFDFirstName.getText() + " " + TFDLastName.getText() + "wurde erfolgreich angelegt.");
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (TFDFirstName.getText().isEmpty()){
                    TFDFirstName.setStyle("-fx-border-color: red;");
                }
                if (TFDLastName.getText().isEmpty()){
                    TFDLastName.setStyle("-fx-border-color: red;");
                }
                if (CBXJavaLevel.getSelectionModel().isEmpty()){
                    CBXJavaLevel.setStyle("-fx-border-color: red;");
                }
                if (CBXCourse.getSelectionModel().isEmpty()){
                    CBXCourse.setStyle("-fx-border-color: red;");
                }
                if (TFDCompany.getText().isEmpty()){
                    TFDCompany.setStyle("-fx-border-color: red;");
                }
            }
        });

        TFDFirstName.setOnKeyPressed(e -> TFDFirstName.setStyle("-fx-border-width: 0px;"));

        TFDLastName.setOnKeyPressed(e -> TFDLastName.setStyle("-fx-border-width: 0px;"));

        CBXJavaLevel.setOnAction(event -> CBXJavaLevel.setStyle("-fx-border-width: 0px;"));

        CBXCourse.setOnAction(event -> CBXCourse.setStyle("-fx-border-width: 0px;"));

        TFDCompany.setOnKeyPressed(e -> TFDCompany.setStyle("-fx-border-width: 0px;"));




    }

    private void initComboBoxes() throws IOException, InterruptedException {
        CBXJavaLevel.setItems(FXCollections.observableArrayList(JavaLevel.values()));
        CBXCourse.setItems(FXCollections.observableArrayList(CourseService.getCourses().stream().map(Course::getId).toList()));

    }

    private void resetCBX() {
        CBXCourse.getSelectionModel().clearSelection();
        CBXJavaLevel.getSelectionModel().clearSelection();
    }
}

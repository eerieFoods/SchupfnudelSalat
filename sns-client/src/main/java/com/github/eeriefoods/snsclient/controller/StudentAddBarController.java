package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import com.github.eeriefoods.snsclient.model.PromptButtonCell;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class StudentAddBarController {
    @FXML private Button BTNSave;
    @FXML private Button BTNCancel;
    //@FXML private TextField TFDStudentId;
    @FXML private TextField TFDFirstName;
    @FXML private TextField TFDLastName;
    @FXML private ComboBox<JavaLevel> CBXJavaLevel;
    @FXML private ComboBox<String> CBXCourse;
    @FXML private TextField TFDCompany;
    private MainController mainController;
    private StudentTableController studentTableController;
    private TableView<Student> studentTableView;
    private StudentToolBarController studentToolBarController;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
        this.studentTableController = mainController.studentTableController;
        this.studentTableView = studentTableController.TCS_View;
        this.studentToolBarController = mainController.studentToolBarController;
    }

    @FXML public void initialize() {
        initComboBoxes();
        initButtonFunctions();
    }

    private void initButtonFunctions() {
        BTNCancel.setOnAction(event -> {
            resetInput();
            mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
        });

        BTNSave.setOnAction(event -> {
            if (!TFDFirstName.getText().isEmpty() && !TFDLastName.getText().isEmpty() && !CBXJavaLevel.getSelectionModel().isEmpty() && !CBXCourse.getSelectionModel().isEmpty() &&!TFDCompany.getText().isEmpty())
            {
                try {
                    Student student = new Student(null, TFDFirstName.getText(), TFDLastName.getText(), CBXJavaLevel.getValue(), TFDCompany.getText(), CBXCourse.getValue());
                    StudentService.createStudent(student);
                    studentTableView.getItems().setAll(studentTableController.loadStudentList());
                    resetInput();
                    mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
                    NotificationHandler.showUserNotification("Student:in erfolgreich angelegt!", "Der Student/die Studentin " + TFDFirstName.getText() + " " + TFDLastName.getText() + " wurde erfolgreich angelegt.");
                    studentToolBarController.updateFilteredList();
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
                if (CBXJavaLevel.getSelectionModel().getSelectedItem() == null){
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

    private void initComboBoxes() {
        CBXJavaLevel.setItems(FXCollections.observableArrayList(JavaLevel.values()));
        CBXJavaLevel.setButtonCell(new PromptButtonCell<>("Java Level"));
        CBXCourse.setItems(FXCollections.observableArrayList(CourseService.getCourses().stream().map(Course::getId).toList()));
        CBXCourse.setButtonCell(new PromptButtonCell<>("Kurse"));
    }

    private void resetInput() {
        CBXCourse.getSelectionModel().clearSelection();
        CBXCourse.getEditor().setPromptText("Test");
        CBXJavaLevel.getSelectionModel().select(null);

        TFDFirstName.clear();
        TFDLastName.clear();
        TFDCompany.clear();



    }
}

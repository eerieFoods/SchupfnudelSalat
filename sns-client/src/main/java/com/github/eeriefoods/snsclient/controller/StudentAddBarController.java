package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import com.github.eeriefoods.snsclient.model.PromptButtonCell;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.github.eeriefoods.snsclient.shared.NotificationHandler.handleExceptionError;

public class StudentAddBarController {
    @FXML
    private Button BTNSave;
    @FXML
    private Button BTNCancel;
    @FXML
    private TextField TFDStudentId;
    @FXML
    private TextField TFDFirstName;
    @FXML
    private TextField TFDLastName;
    @FXML
    private ComboBox<JavaLevel> CBXJavaLevel;
    @FXML
    private ComboBox<String> CBXCourse;
    @FXML
    private TextField TFDCompany;
    private MainController mainController;
    private StudentTableController studentTableController;
    private TableView<Student> studentTableView;
    private StudentToolBarController studentToolBarController;

    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
        this.studentTableController = mainController.studentTableController;
        this.studentTableView = studentTableController.TCS_View;
        this.studentToolBarController = mainController.studentToolBarController;
    }

    @FXML
    public void initialize() {
        initComboBoxes();
        initButtonFunctions();
    }

    private void initButtonFunctions() {

        // force the field to be numeric only
        TFDStudentId.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                TFDStudentId.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (TFDStudentId.getText().length() > 6) {
                String s = TFDStudentId.getText().substring(0, 6);
                TFDStudentId.setText(s);
            }
        });

        BTNCancel.setOnAction(event -> {
            resetInput();
            mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
        });

        BTNSave.setOnAction(event -> {
            if ((TFDStudentId.getText().isEmpty() || TFDStudentId.getText().length() == 6) && !TFDFirstName.getText().isEmpty() && !TFDLastName.getText().isEmpty() && !CBXJavaLevel.getSelectionModel().isEmpty() && !CBXCourse.getSelectionModel().isEmpty() && !TFDCompany.getText().isEmpty()) {
                try {

                    Student student = new Student(TFDStudentId.getText().isEmpty() ? null : Integer.parseInt(TFDStudentId.getText()), TFDFirstName.getText(), TFDLastName.getText(), CBXJavaLevel.getValue(), TFDCompany.getText(), CBXCourse.getValue());
                    student = StudentService.createStudent(student);
                    CourseService.addMemberToCourse(CBXCourse.getValue(), student);
                    studentTableView.getItems().setAll(studentTableController.loadStudentList());
                    mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
                    NotificationHandler.showUserNotification("Student:in erfolgreich angelegt!", "Der Student/die Studentin " + TFDFirstName.getText() + " " + TFDLastName.getText() + " wurde erfolgreich angelegt.");
                    resetInput();
                    studentToolBarController.updateFilteredList();
                } catch (IOException | InterruptedException e) {
                    handleExceptionError(e.getStackTrace());
                }
            } else {
                if (!TFDStudentId.getText().isEmpty() && TFDStudentId.getText().length() != 6) {
                    TFDStudentId.setStyle(STYLE_RED_BORDER);
                }


                if (TFDFirstName.getText().isEmpty()) {
                    TFDFirstName.setStyle("-fx-border-color: red;");
                }
                if (TFDLastName.getText().isEmpty()) {
                    TFDLastName.setStyle("-fx-border-color: red;");
                }
                if (CBXJavaLevel.getSelectionModel().getSelectedItem() == null) {
                    CBXJavaLevel.setStyle("-fx-border-color: red;");
                }
                if (CBXCourse.getSelectionModel().isEmpty()) {
                    CBXCourse.setStyle("-fx-border-color: red;");
                }
                if (TFDCompany.getText().isEmpty()) {
                    TFDCompany.setStyle("-fx-border-color: red;");
                }
            }
        });

        TFDStudentId.setOnKeyPressed(e -> TFDStudentId.setStyle(STYLE_NO_BORDER));

        TFDFirstName.setOnKeyPressed(e -> TFDFirstName.setStyle(STYLE_NO_BORDER));

        TFDLastName.setOnKeyPressed(e -> TFDLastName.setStyle("-fx-border-width: 0px;"));

        CBXJavaLevel.setOnAction(event -> CBXJavaLevel.setStyle("-fx-border-width: 0px;"));

        CBXCourse.setOnAction(event -> CBXCourse.setStyle("-fx-border-width: 0px;"));

        TFDCompany.setOnKeyPressed(e -> TFDCompany.setStyle("-fx-border-width: 0px;"));
    }

    private void initComboBoxes() {
        CBXJavaLevel.setItems(FXCollections.observableArrayList(JavaLevel.values()));
        CBXJavaLevel.setButtonCell(new PromptButtonCell<>("Java Kenntnisse"));
        CBXCourse.setItems(FXCollections.observableArrayList(CourseService.getCourses().stream().map(Course::getId).toList()));
        CBXCourse.setButtonCell(new PromptButtonCell<>("Kurse"));
    }

    private void resetInput() {
        CBXCourse.getSelectionModel().clearSelection();
        CBXCourse.getEditor().setPromptText("Test");
        CBXJavaLevel.getSelectionModel().select(null);
        TFDStudentId.clear();
        TFDFirstName.clear();
        TFDLastName.clear();
        TFDCompany.clear();


    }
}

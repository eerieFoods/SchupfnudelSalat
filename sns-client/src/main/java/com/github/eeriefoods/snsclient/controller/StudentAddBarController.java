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
import java.util.List;

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

    private final String STYLE_NO_BORDER = "-fx-border-width: 0px;";
    private final String STYLE_RED_BORDER = "-fx-border-color: red;";

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
                    TFDFirstName.setStyle(STYLE_RED_BORDER);
                }
                if (TFDLastName.getText().isEmpty()) {
                    TFDLastName.setStyle(STYLE_RED_BORDER);
                }
                if (CBXJavaLevel.getSelectionModel().getSelectedItem() == null) {
                    CBXJavaLevel.setStyle(STYLE_RED_BORDER);
                }
                if (CBXCourse.getSelectionModel().isEmpty()) {
                    CBXCourse.setStyle(STYLE_RED_BORDER);
                }
                if (TFDCompany.getText().isEmpty()) {
                    TFDCompany.setStyle(STYLE_RED_BORDER);
                }
            }
        });

        TFDStudentId.setOnKeyPressed(e -> TFDStudentId.setStyle(STYLE_NO_BORDER));

        TFDFirstName.setOnKeyPressed(e -> TFDFirstName.setStyle(STYLE_NO_BORDER));

        TFDLastName.setOnKeyPressed(e -> TFDLastName.setStyle(STYLE_NO_BORDER));

        CBXJavaLevel.setOnAction(event -> CBXJavaLevel.setStyle(STYLE_NO_BORDER));

        CBXCourse.setOnAction(event -> CBXCourse.setStyle(STYLE_NO_BORDER));

        TFDCompany.setOnKeyPressed(e -> TFDCompany.setStyle(STYLE_NO_BORDER));
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
        TFDStudentId.setStyle(STYLE_NO_BORDER);
        TFDFirstName.setStyle(STYLE_NO_BORDER);
        TFDLastName.setStyle(STYLE_NO_BORDER);
        CBXJavaLevel.setStyle(STYLE_NO_BORDER);
        CBXCourse.setStyle(STYLE_NO_BORDER);
        TFDCompany.setStyle(STYLE_NO_BORDER);
    }
}

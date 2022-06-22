package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

public class CourseAddBarController {
    @FXML
    private Button BTNSave;
    @FXML
    private Button BTNCancel;
    @FXML
    private TextField TFDCourseId;
    @FXML
    private TextField TFDRoom;
    private MainController mainController;
    private CourseTableController courseTableController;
    private TableView<Course> courseTableView;

    public void injectMainController(@NotNull MainController mainController) {
        this.mainController = mainController;
        this.courseTableController = mainController.courseTableController;
        this.courseTableView = courseTableController.TCCView;
    }

    @FXML
    public void initialize() {
        initTextFieldFunctions();
    }

    private void initTextFieldFunctions() {
        BTNCancel.setOnAction(event -> mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem()));

        BTNSave.setOnAction(event -> {
            if (!TFDCourseId.getText().isEmpty() && !TFDRoom.getText().isEmpty()) {
                Course course = new Course(TFDCourseId.getText(), TFDRoom.getText());
                CourseService.createCourse(course);
                courseTableView.getItems().setAll(courseTableController.loadCourseList());
                mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
                NotificationHandler.showUserNotification("Kurs angelegt", "Kurs " + TFDCourseId.getText() + " wurde erfolgreich angelegt!");
                mainController.studentAddBarController.setCBXCourseItems();
                TFDCourseId.clear();
                TFDRoom.clear();
            } else {
                if (TFDCourseId.getText().isEmpty()) {
                    TFDCourseId.setStyle("-fx-border-color: red;");
                }

                if (TFDRoom.getText().isEmpty()) {
                    TFDRoom.setStyle("-fx-border-color: red;");
                }

                TFDCourseId.setOnKeyPressed(e -> TFDCourseId.setStyle("-fx-border-width: 0px;"));
                TFDRoom.setOnKeyPressed(e -> TFDRoom.setStyle("-fx-border-width: 0px;"));
            }
        });
    }
}

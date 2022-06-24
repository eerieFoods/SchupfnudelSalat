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

    private final String STYLE_NO_BORDER = "-fx-border-width: 0px;";
    private final String STYLE_RED_BORDER = "-fx-border-color: red;";

    public void injectMainController(@NotNull MainController mainController) {
        this.mainController = mainController;
        this.courseTableController = mainController.courseTableController;
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
                mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
                mainController.studentAddBarController.setCBXCourseItems();
                courseTableController.updateCourseTable();
                TFDCourseId.clear();
                TFDRoom.clear();
            } else {
                if (TFDCourseId.getText().isEmpty()) {
                    TFDCourseId.setStyle(STYLE_RED_BORDER);
                }

                if (TFDRoom.getText().isEmpty()) {
                    TFDRoom.setStyle(STYLE_RED_BORDER);
                }

                TFDCourseId.setOnKeyPressed(e -> TFDCourseId.setStyle(STYLE_NO_BORDER));
                TFDRoom.setOnKeyPressed(e -> TFDRoom.setStyle(STYLE_NO_BORDER));
            }
        });
    }
}

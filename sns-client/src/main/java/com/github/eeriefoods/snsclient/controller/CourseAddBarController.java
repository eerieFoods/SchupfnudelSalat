package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.shared.NotificationHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CourseAddBarController {
    @FXML private Button BTNSave;
    @FXML private Button BTNCancel;
    @FXML private TextField TFDCourseId;
    @FXML private TextField TFDFriendlyName;
    @FXML private TextField TFDRoom;
    private MainController mainController;
    private CourseTableController courseTableController;
    private TableView<Course> courseTableView;

    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
        this.courseTableController = mainController.courseTableController;
        this.courseTableView = courseTableController.TCK_View;
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {

        BTNCancel.setOnAction(event -> mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem()));

        BTNSave.setOnAction(event -> {

            if (!TFDCourseId.getText().isEmpty() && !TFDFriendlyName.getText().isEmpty() && !TFDRoom.getText().isEmpty())
            {
                Course course = new Course("123", "123", "123gb");
                try {
                    CourseService.createCourse(course);
                    courseTableView.getItems().setAll(courseTableController.loadCourseList());
                    mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                NotificationHandler.showUserNotification("successful", "Course " + TFDFriendlyName.getText() + "has been saved successfully!");
            } else {
                if (TFDCourseId.getText().isEmpty()) {
                    TFDCourseId.setStyle("-fx-border-color: red;");
                }

                if (TFDFriendlyName.getText().isEmpty()) {
                    TFDFriendlyName.setStyle("-fx-border-color: red;");
                }

                if (TFDRoom.getText().isEmpty()) {
                    TFDRoom.setStyle("-fx-border-color: red;");
                }
            }

            TFDCourseId.setOnKeyPressed(e -> {
                TFDCourseId.setStyle("-fx-border-width: 0px;");
            });

            TFDFriendlyName.setOnKeyPressed(e -> {
                TFDFriendlyName.setStyle("-fx-border-width: 0px;");
            });

            TFDRoom.setOnKeyPressed(e -> {
                TFDRoom.setStyle("-fx-border-width: 0px;");
            });


        });
    }
}

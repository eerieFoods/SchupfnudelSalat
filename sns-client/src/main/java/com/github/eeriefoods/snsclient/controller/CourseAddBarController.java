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

import java.io.IOException;

public class CourseAddBarController {
    @FXML
    private Button BTNSave;
    @FXML private Button BTNCancel;
    @FXML private TextField TFDFirstName;
    @FXML private TextField TFDLastName;
    @FXML private TextField TFDCompany;
    private MainController mainController;
    private CourseTableController courseTableController;
    private TableView<Course> courseTableView;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
        this.courseTableController = mainController.courseTableController;
        this.courseTableView = courseTableController.TCK_View;
    }

    @FXML public void initialize() throws IOException, InterruptedException {

        BTNCancel.setOnAction(event -> mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem()));

        BTNSave.setOnAction(event -> {
            Course course = new Course("123","123","123gb");
            try {
                CourseService.createCourse(course);
                courseTableView.getItems().setAll(courseTableController.loadCourseList());
                mainController.switchBar(mainController.tabPane.getSelectionModel().getSelectedItem());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

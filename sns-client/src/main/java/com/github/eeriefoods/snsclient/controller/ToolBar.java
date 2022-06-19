package com.github.eeriefoods.snsclient.controller;


import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class ToolBar {
    @FXML AnchorPane toolBar;
    @FXML Button BTNCreate;
    @FXML Button BTNDelete;
    @FXML TextField TFDSearch;
    @FXML Button BTNSearch;

    MainController mainController;
    CourseTableController courseTableController;
    TableView<Course> courseTableView;
    StudentTableController studentTableController;
    TableView<Student> studentTableView;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
        this.studentTableController = mainController.studentTableController;
        this.studentTableView = studentTableController.TCS_View;
        this.courseTableController = mainController.courseTableController;
        this.courseTableView = courseTableController.TCK_View;
    }
    @FXML public void initialize(){

        initButtonFunctions();
    }

    public void initButtonFunctions(){

    }

    void studentSearch() {
        try {
            FilteredList<Student> filteredData = new FilteredList<>(FXCollections.observableList(StudentService.getAllStudents()));
            studentTableView.setItems(filteredData);
            filteredData.setPredicate(studentTableController.createPredicate(TFDSearch.getText()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    void courseSearch() {
        try {
            FilteredList<Course> filteredData = new FilteredList<>(FXCollections.observableList(CourseService.getCourses()));
            courseTableView.setItems(filteredData);
            filteredData.setPredicate(courseTableController.createPredicate(TFDSearch.getText()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

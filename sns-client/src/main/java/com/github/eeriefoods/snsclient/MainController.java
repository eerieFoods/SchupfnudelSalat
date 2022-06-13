package com.github.eeriefoods.snsclient;

import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.eeriefoods.snsclient.model.*;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MainController {

    @FXML private TableView<Student> TCS_View;
    @FXML private TableColumn<Student, Integer> TCS_ID;
    @FXML private TableColumn<Student, String> TCS_FirstName;
    @FXML private TableColumn<Student, String> TCS_LastName;
    @FXML private TableColumn<Student, String> TCS_Course;
    @FXML private TableColumn<Student, String> TCS_Company;
    @FXML private TableColumn<Student, JavaLevel> TCS_JavaLevel;

    @FXML private TableView<Course> TCK_View;
    @FXML private TableColumn<Course, Integer> TCK_ID;
    @FXML private TableColumn<Course, String> TCK_FriendlyName;
    @FXML private TableColumn<Course, String> TCK_Room;

    @FXML private void initialize() {
        
        TCS_ID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        TCS_FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TCS_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TCS_LastName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_JavaLevel.setCellValueFactory(new PropertyValueFactory<>("javaLevel"));
//        TCS_JavaLevel.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_Company.setCellValueFactory(new PropertyValueFactory<>("company"));
        TCS_Company.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_Course.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        TCS_Course.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_View.getItems().setAll(parseStudentList());

        TCK_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TCK_FriendlyName.setCellValueFactory(new PropertyValueFactory<>("friendlyName"));
        TCK_Room.setCellValueFactory(new PropertyValueFactory<>("room"));
        TCK_View.getItems().setAll(parseCourseList());

        TCS_FirstName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(event.getNewValue());
            updateStudent(event.getRowValue());});
        TCS_LastName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(event.getNewValue());
            updateStudent(event.getRowValue());});
        TCS_Company.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCompany(event.getNewValue());
            updateStudent(event.getRowValue());});
        TCS_Course.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCourse(event.getNewValue());
            updateStudent(event.getRowValue());});

        TCS_View.setEditable(true);

        TCS_JavaLevel.setCellValueFactory(i -> {
            final JavaLevel javaLevel = i.getValue().getJavaLevel();
            // binding to constant value
            return Bindings.createObjectBinding(() -> javaLevel);
        });

        TCS_JavaLevel.setCellFactory(col -> {
            TableCell<Student, JavaLevel> c = new TableCell<>();
            final ComboBox<JavaLevel> comboBox = new ComboBox<>();
            comboBox.setItems(FXCollections.observableArrayList( JavaLevel.values()));
            c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
            return c;});

    }

    private void updateStudent(Student student){
            try {
                StudentService.updateStudent(student);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
    }

    private List<Course> parseCourseList() {
        try {
            return CourseService.getCourses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }    }

    private List<Student> parseStudentList() {
        try {
            return StudentService.getAllStudents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
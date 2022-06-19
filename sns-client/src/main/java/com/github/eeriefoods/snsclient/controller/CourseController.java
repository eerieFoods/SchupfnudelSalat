package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.service.CourseService;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.List;

public class CourseController {
    @FXML public TableView<Course> TCK_View;
    @FXML private TableColumn<Course, String> TCK_ID;
    @FXML private TableColumn<Course, String> TCK_FriendlyName, TCK_Room;

    @FXML private void initialize() {

        initTableCellFactories();
        initTableCellEditi();
    }

    private void initTableCellEditi() {
        TCK_ID.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setId(event.getNewValue());
            updateCourse(event.getRowValue());
        });
        TCK_FriendlyName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setFriendlyName(event.getNewValue());
            updateCourse(event.getRowValue());
        });
        TCK_Room.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setRoom(event.getNewValue());
            updateCourse(event.getRowValue());
        });
    }

    private void initTableCellFactories() {
        TCK_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TCK_ID.setCellFactory(TextFieldTableCell.forTableColumn());
        TCK_FriendlyName.setCellValueFactory(new PropertyValueFactory<>("friendlyName"));
        TCK_FriendlyName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCK_Room.setCellValueFactory(new PropertyValueFactory<>("room"));
        TCK_Room.setCellFactory(TextFieldTableCell.forTableColumn());
        TCK_View.getItems().setAll(loadCourseList());
    }

    private List<Course> loadCourseList() {
        try {
            return CourseService.getCourses();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCourse(Course course){
        try {
            CourseService.updateCourse(course);
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }

}

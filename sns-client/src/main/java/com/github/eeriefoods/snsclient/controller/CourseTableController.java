package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class CourseTableController {
    @FXML public TableView<Course> TCK_View;
    @FXML private TableColumn<Course, String> TCK_ID;
    @FXML private TableColumn<Course, String> TCK_FriendlyName, TCK_Room;

    private MainController mainController;
    private StudentToolBarController studentToolBarController;
    private StudentTableController studentTableController;
    private TableView<Student> studentTableView;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
        this.studentTableController = mainController.studentTableController;
        this.studentTableView = mainController.studentTableController.TCS_View;
        this.studentToolBarController = mainController.studentToolBarController;
    }

    @FXML private void initialize() {

        initTableCellFactories();
        initTableCellEditi();

        TCK_View.getItems().setAll(loadCourseList());
        TCK_View.setEditable(true);
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

    }

    public List<Course> loadCourseList() {
        try {
            return CourseService.getCourses();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCourse(Course course){
        try {
            CourseService.updateCourse(course);
            studentTableView.getItems().setAll(studentTableController.loadStudentList());
            studentToolBarController.updateFilteredList();
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteCourse(Course course){
        try {
            CourseService.deleteCourse(course.getId());
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
    private boolean searchFindsOrder(Course course, String searchText){
        return (course.getId().contains(searchText.toLowerCase())) ||
                (course.getFriendlyName().toLowerCase().contains(searchText.toLowerCase())) ||
                (course.getRoom().toLowerCase().contains(searchText.toLowerCase()));
    }

    public Predicate<Course> createPredicate(String searchText) {
        return course -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(course, searchText);
        };
    }

}

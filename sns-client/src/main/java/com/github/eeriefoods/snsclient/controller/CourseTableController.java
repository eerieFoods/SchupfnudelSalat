package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;

import com.github.eeriefoods.snsclient.shared.NotificationHandler;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class CourseTableController {
    @FXML public TableView<Course> TCCView;
    @FXML private TableColumn<Course, String> TCCId, TCCRoom;

    @FXML private TableColumn<Course, Integer> TCCMemberCount;

    private StudentToolBarController studentToolBarController;
    private StudentTableController studentTableController;
    private TableView<Student> studentTableView;

    public void injectMainController(MainController mainController){
        this.studentTableController = mainController.studentTableController;
        this.studentTableView = mainController.studentTableController.TCS_View;
        this.studentToolBarController = mainController.studentToolBarController;
    }

    @FXML private void initialize() {

        initTableCellFactories();
        initTableCellEdit();
        updateCourseTable();
        TCCView.setEditable(true);
    }

    private void initTableCellEdit() {
        TCCId.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setId(event.getNewValue());
                updateCourse(event.getRowValue());

        });

        TCCRoom.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setRoom(event.getNewValue());
                updateCourse(event.getRowValue());

        });
    }

    private void initTableCellFactories() {
        TCCId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TCCId.setCellFactory(TextFieldTableCell.forTableColumn());
        TCCRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        TCCRoom.setCellFactory(TextFieldTableCell.forTableColumn());
        TCCMemberCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TCCMemberCount.setCellValueFactory(new PropertyValueFactory<>("memberCount"));
    }

    public List<Course> loadCourseList() throws IOException, InterruptedException{
            return CourseService.getCourses();
    }

    private void updateCourse(Course course){ //REMOVE EXCEPTIONS HERE!
            CourseService.updateCourse(course);
            studentTableView.getItems().setAll(studentTableController.loadStudentList());
            studentToolBarController.updateFilteredList();

    }

    public void deleteCourse(Course course){
            CourseService.deleteCourse(course.getId());
    }

    private boolean searchFindsOrder(Course course, String searchText){
        return (course.getId().toLowerCase().contains(searchText.toLowerCase())) ||
                (course.getMemberCount().toString().contains(searchText.toLowerCase())) ||
                (course.getRoom().toLowerCase().contains(searchText.toLowerCase()));
    }

    public Predicate<Course> createPredicate(String searchText) {
        return course -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(course, searchText);
        };
    }

    public void updateCourseTable(){
        try {
            TCCView.getItems().setAll(loadCourseList());
        } catch (IOException | InterruptedException e) {
            NotificationHandler.handleExceptionError(e);
        }
    }
}

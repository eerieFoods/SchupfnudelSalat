package com.github.eeriefoods.snsclient.controller;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.service.CourseService;
import com.github.eeriefoods.snsclient.service.StudentService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;


public class StudentTableController {
    @FXML public TableView<Student> TCS_View;
    @FXML private TableColumn<Student, Integer> TCS_ID;
    @FXML private TableColumn<Student, String> TCS_FirstName, TCS_LastName, TCS_Company, TCS_Course;
    @FXML private TableColumn<Student, JavaLevel> TCS_JavaLevel;
    private StudentToolBarController studentToolBarController;
    public void injectMainController(MainController mainController){
        this.studentToolBarController = mainController.studentToolBarController;
    }
    @FXML private void initialize() throws IOException, InterruptedException {

        initTableCellFactories();
        initTableCellEdits();

        TCS_View.getItems().setAll(loadStudentList());
        TCS_View.setEditable(true);

    }



    private void initTableCellFactories() {
        TCS_ID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        TCS_FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TCS_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TCS_LastName.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_JavaLevel.setCellValueFactory(new PropertyValueFactory<>("javaLevel"));
        TCS_JavaLevel.setCellFactory(ComboBoxTableCell.forTableColumn(JavaLevel.values()));
        TCS_Company.setCellValueFactory(new PropertyValueFactory<>("company"));
        TCS_Company.setCellFactory(TextFieldTableCell.forTableColumn());
        TCS_Course.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        String[] courses = CourseService
                .getCourses()
                .stream()
                .map(Course::getId)
                .toArray(String[]::new);

        TCS_Course.setCellFactory(ComboBoxTableCell.forTableColumn(courses));
    }

    private void initTableCellEdits() {
        TCS_FirstName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(event.getNewValue());
            updateStudent(event.getRowValue());
        });
        TCS_LastName.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(event.getNewValue());
            updateStudent(event.getRowValue());
        });
        TCS_Company.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCompany(event.getNewValue());
            updateStudent(event.getRowValue());
        });
        TCS_Course.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCourse(event.getNewValue());
            updateStudent(event.getRowValue());
        });
        TCS_JavaLevel.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setJavaLevel(event.getNewValue());
            updateStudent(event.getRowValue());
        });
    }

    private void updateStudent(Student student){
            StudentService.updateStudent(student); //TODO Friendly Name machen
            studentToolBarController.updateFilteredList();

    }

    public void deleteStudent(Student student) {
            StudentService.deleteStudent(student.getStudentId().toString());
            TCS_View.getItems().remove(student);

    }

    public List<Student> loadStudentList() throws IOException, InterruptedException {
            return StudentService.getAllStudents();
    }

    private boolean searchFindsOrder(Student student, String searchText){
        return (student.getStudentId().toString().contains(searchText.toLowerCase())) ||
                (student.getFirstName().toLowerCase().contains(searchText.toLowerCase())) ||
                (student.getLastName().toLowerCase().contains(searchText.toLowerCase())) ||
                (student.getJavaLevel().toString().toLowerCase().contains(searchText.toLowerCase())) ||
                (student.getCourseId().toLowerCase().contains(searchText.toLowerCase())) ||
                (student.getCompany().toLowerCase().contains(searchText.toLowerCase()));
    }

    public Predicate<Student> createPredicate(String searchText) {
        return student -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(student, searchText);
        };
    }
}

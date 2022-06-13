package com.github.eeriefoods.snsclient;

import com.github.eeriefoods.snsclient.model.Course;
import com.github.eeriefoods.snsclient.model.JavaLevel;
import com.github.eeriefoods.snsclient.model.Student;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainController {

    @FXML
    private TableView<Student> TCS_View;
    @FXML
    private TableColumn<Student, Integer> TCS_ID;
    @FXML
    private TableColumn<Student, String> TCS_FirstName;
    @FXML
    private TableColumn<Student, String> TCS_LastName;
    @FXML
    private TableColumn<Student, String> TCS_Course;
    @FXML
    private TableColumn<Student, String> TCS_Company;
    @FXML
    private TableColumn<Student, JavaLevel> TCS_JavaLevel;

    @FXML
    private void initialize() {

        TCS_ID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        TCS_FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TCS_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TCS_JavaLevel.setCellValueFactory(new PropertyValueFactory<>("javaLevel"));
        TCS_Company.setCellValueFactory(new PropertyValueFactory<>("company"));
        TCS_Course.setCellValueFactory(new PropertyValueFactory<>("course"));
        TCS_View.getItems().setAll(parseStudentList());
        TCS_View.setRowFactory(
                new Callback<TableView<Student>, TableRow<Student>>() {
                    @Override
                    public TableRow<Student> call(TableView<Student> tableView) {
                        final TableRow<Student> row = new TableRow<>();
                        final ContextMenu rowMenu = new ContextMenu();
                        MenuItem addItem = new MenuItem("Add");
                        addItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                CTXAdd();
                            }
                        });
                        MenuItem editItem = new MenuItem("Edit");
                        editItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                CTXEdit(String.valueOf(row.getItem().getStudentId()));
                            }
                        });
                        MenuItem removeItem = new MenuItem("Delete");
                        removeItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                CTXDelete(String.valueOf(row.getItem().getStudentId()));
                            }
                        });
                        rowMenu.getItems().addAll(editItem, removeItem);

                        // only display context menu for non-null items:
                        row.contextMenuProperty().bind(
                                Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                        .then(rowMenu)
                                        .otherwise((ContextMenu)null));
                        return row;
                    }
                });

    }


    private List<Student> parseStudentList() {
        Course Test = new Course("TINF21AI2", "TINF21AI2", "123B");
        List<Student> Students = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 200; i++) {
            Student iStudent = new Student(rand.nextInt(999999), "ASDF", String.valueOf(i), JavaLevel.SEHR_GUT, i + " NONAMECOMPANY", Test);
            Students.add(iStudent);
        }
        return Students;
    }

    public void CTXAdd() {
        //Add Button was clicked. Do shit here!
        showMessageDialog(null, "In Theory, we could create a new Student here. \n We don't tho.");
    }

    public void CTXEdit(String studentId) {
        //Add Button was clicked. Do shit here!
        showMessageDialog(null, "In Theory, we could edit the Student with the ID: " + studentId + " here. \n We don't tho.");
    }

    public void CTXDelete(String studentId) {
        //Add Button was clicked. Do shit here!
        showMessageDialog(null, "In Theory, we could delete the Student with the ID: " + studentId + " here. \n We don't tho.");
    }
}



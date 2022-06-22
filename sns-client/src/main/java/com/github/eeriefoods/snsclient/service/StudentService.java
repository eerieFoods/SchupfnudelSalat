package com.github.eeriefoods.snsclient.service;

import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.shared.HttpFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.github.eeriefoods.snsclient.shared.Constants.getServerUri;
import static com.github.eeriefoods.snsclient.shared.NotificationHandler.handleExceptionError;

public class StudentService {

    private static final Gson gson;
    private static final String ENDPOINT = "student";

    static {
        gson = new Gson();
    }

    public static List<Student> getAllStudents() {
        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendGetRequest(getServerUri(ENDPOINT));
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }
        assert response != null;
        return gson.fromJson(response.body(), new TypeToken<ArrayList<Student>>(){}.getType());
    }

    public static Student getStudent(String studentId) {
        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendGetRequest(getServerUri("%s/%s".formatted(ENDPOINT, studentId)));
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }
        assert response != null;
        return gson.fromJson(response.body(), Student.class);
    }

    public static Student createStudent(Student student) {
        String requestBody = gson.toJson(student);
        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendPostJsonRequest(getServerUri(ENDPOINT), requestBody);
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }

        assert response != null;
        return gson.fromJson(response.body(), Student.class);
    }

    public static Student updateStudent(Student student) {
        String requestBody = gson.toJson(student);
        HttpResponse<String> response = null;
        try {
            response = HttpFactory.sendPutJsonRequest(getServerUri(ENDPOINT), requestBody);
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }
        assert response != null;
        return gson.fromJson(response.body(), Student.class);    }

    public static void deleteStudent(String studentId) {
        try {
            HttpFactory.sendDeleteRequest(getServerUri("%s/%s".formatted(ENDPOINT, studentId)));
        } catch (IOException | InterruptedException e) {
            handleExceptionError(e.getStackTrace());
        }
    }

}

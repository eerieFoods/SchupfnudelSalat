package com.github.eeriefoods.snsclient.service;

import com.github.eeriefoods.snsclient.model.Student;
import com.github.eeriefoods.snsclient.shared.HttpFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpResponse;

import static com.github.eeriefoods.snsclient.shared.Constants.getServerUri;

public class StudentService {

    private static final Gson gson;
    private static final String ENDPOINT = "student";

    static {
        gson = new Gson();
    }

    public Student getStudent(String studentId) throws IOException, InterruptedException {
        HttpResponse<String> response = HttpFactory.sendGetRequest(getServerUri("%s/%s".formatted(ENDPOINT, studentId)));

        return gson.fromJson(response.body(), Student.class);
    }

    public Student createStudent(Student student) throws IOException, InterruptedException {
        String requestBody = gson.toJson(student);

        HttpResponse<String> response = HttpFactory.sendPostJsonRequest(getServerUri(ENDPOINT), requestBody);

        return gson.fromJson(response.body(), Student.class);
    }

    public Student updateStudent(Student student) throws IOException, InterruptedException {
        String requestBody = gson.toJson(student);

        HttpResponse<String> response = HttpFactory.sendPutJsonRequest(getServerUri(ENDPOINT), requestBody);

        return gson.fromJson(response.body(), Student.class);    }

    public void deleteStudent(String studentId) throws IOException, InterruptedException {
        HttpFactory.sendDeleteRequest(getServerUri("%s/%s".formatted(ENDPOINT, studentId)));
    }

}
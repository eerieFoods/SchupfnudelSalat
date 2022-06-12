package com.github.eeriefoods.snsserver.student.domain;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.student.api.StudentData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest extends BaseMockitoTest {

    @InjectMocks
    private StudentFactory studentFactory;

    @Test
    void testFrom() {
        StudentData studentData = new StudentData();
    }
}
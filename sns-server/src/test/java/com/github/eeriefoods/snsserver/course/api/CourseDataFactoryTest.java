package com.github.eeriefoods.snsserver.course.api;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.course.domain.Course;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CourseDataFactoryTest extends BaseMockitoTest {

    @Mock
    private Course actual;
    @InjectMocks
    private CourseDataFactory courseDataFactory;

    @Test
    void testFrom() {
        when(actual.getId()).thenReturn("CourseId");
        when(actual.getRoom()).thenReturn("123B");
        when(actual.getMembers()).thenReturn(Collections.emptySet());

        CourseData expected = courseDataFactory.from(actual);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMembers(), actual.getMembers());
        assertEquals(expected.getRoom(), actual.getRoom());
    }
}
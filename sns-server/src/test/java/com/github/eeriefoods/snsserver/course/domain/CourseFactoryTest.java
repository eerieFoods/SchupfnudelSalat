package com.github.eeriefoods.snsserver.course.domain;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.course.api.CourseData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CourseFactoryTest extends BaseMockitoTest {

    @InjectMocks
    private CourseFactory courseFactory;

    @Test
    void testFrom() {
        CourseData actual = new CourseData();
        actual.setId("CourseId");
        actual.setMembers(Collections.emptySet());
        actual.setFriendlyMame("FN");
        actual.setRoom("123B");

        Course expected = courseFactory.from(actual);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMembers(), actual.getMembers());
        assertEquals(expected.getFriendlyName(), actual.getFriendlyMame());
        assertEquals(expected.getRoom(), actual.getRoom());
    }
}
package com.github.eeriefoods.snsserver.course.api;

import base.BaseMockitoTest;
import com.github.eeriefoods.snsserver.course.domain.Course;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

class CourseDataFactoryTest extends BaseMockitoTest {

    @Mock
    private Course expected;
    @InjectMocks
    private CourseDataFactory courseDataFactory;

    @Test
    void testFrom() {
        when(expected.getId()).thenReturn("CourseId");
        when(expected.getRoom()).thenReturn("123B");
        when(expected.getFriendlyName()).thenReturn("FN");
        when(expected.getMembers()).thenReturn(Collections.emptySet());

        CourseData actual = courseDataFactory.from(this.expected);

        assertEquals(actual.getId(), this.expected.getId());
        assertEquals(actual.getMembers(), this.expected.getMembers());
        assertEquals(actual.getRoom(), this.expected.getRoom());
        assertEquals(actual.getFriendlyName(), this.expected.getFriendlyName());
    }

    @Test
    void from() {
        when(expected.getId()).thenReturn("CourseId");
        when(expected.getRoom()).thenReturn("123B");
        when(expected.getFriendlyName()).thenReturn("FN");
        when(expected.getMembers()).thenReturn(Collections.emptySet());

        List<Course> expectedList = List.of(expected);
        List<CourseData> actual = courseDataFactory.from(expectedList);

        assertEquals(expectedList.size(), actual.size());
        assertFalse(actual.isEmpty());

        IntStream.range(0, expectedList.size()).forEach(index -> {
            assertEquals(expectedList.get(index).getId(), actual.get(index).getId());
            assertEquals(expectedList.get(index).getMembers(), actual.get(index).getMembers());
            assertEquals(expectedList.get(index).getRoom(), actual.get(index).getRoom());
            assertEquals(expectedList.get(index).getFriendlyName(), actual.get(index).getFriendlyName());
        });
    }
}
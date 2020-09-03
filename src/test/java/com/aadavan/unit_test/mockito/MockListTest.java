package com.aadavan.unit_test.mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class MockListTest {

    @Test
    public void listSize() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2, listMock.size());
    }

    @Test
    public void multipleValues() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    public void mockGet() {
        List listGet = mock(List.class);
        when(listGet.get(0)).thenReturn("Aadavan");
        assertEquals("Aadavan", listGet.get(0));
        assertEquals(null, listGet.get(1));
    }

    @Test
    public void argumentMatcher() {
        List listGet = mock(List.class);
        when(listGet.get(anyInt())).thenReturn("Aadavan");
        assertEquals("Aadavan", listGet.get(0));
        assertEquals("Aadavan", listGet.get(10));
    }

    @Test
    public void argumentMatcherBDD() {
        //Given
        List<String> listGet = mock(List.class);
        given(listGet.get(anyInt())).willReturn("Aadavan");

        //When
        String value = listGet.get(0);

        //Then
        assertThat(value, CoreMatchers.is("Aadavan"));
    }

    @Test(expected = RuntimeException.class)
    public void exceptionCheck() {
        List listGet = mock(List.class);
        when(listGet.get(anyInt())).thenThrow(new RuntimeException("Something"));
        listGet.get(0);
    }

}

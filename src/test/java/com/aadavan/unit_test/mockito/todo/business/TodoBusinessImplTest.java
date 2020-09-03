package com.aadavan.unit_test.mockito.todo.business;

import com.aadavan.unit_test.mockito.todo.data.TodoService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.BDDMockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoBusinessImplTest {

    @Test
    public void testRetrieveUsingMock() {
        final TodoService mockTodoService = mock(TodoService.class);

        final List<String> defaultList = Arrays.asList("Unit Test", "Develop", "Debug");
        when(mockTodoService.retrieveTodos("Aadavan"))
                .thenReturn(defaultList);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);
        List<String> filteredTodos = todoBusiness.defaultToDoList("Aadavan");
        assertEquals(3, filteredTodos.size());
    }

    @Test
    public void testRetrieveUsingBDD() {
        //Given
        final TodoService mockTodoService = mock(TodoService.class);
        final List<String> defaultList = Arrays.asList("Unit Test", "Develop", "Debug");
        given(mockTodoService.retrieveTodos("Aadavan")).willReturn(defaultList);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);

        // When
        List<String> filteredTodos = todoBusiness.defaultToDoList("Aadavan");

        // Then
        assertThat(filteredTodos.size(), is(3));
    }
}

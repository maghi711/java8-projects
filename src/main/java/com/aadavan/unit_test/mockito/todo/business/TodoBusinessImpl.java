package com.aadavan.unit_test.mockito.todo.business;

import com.aadavan.unit_test.mockito.todo.data.TodoService;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl {
    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> defaultToDoList(String user) {
        List<String> filteredTodos = new ArrayList<>();
        List<String> allList = todoService.retrieveTodos(user);
        allList.forEach(st -> {
            if (st.length() > 2) {
                filteredTodos.add(st);
            }
        });
        return filteredTodos;
    }
}

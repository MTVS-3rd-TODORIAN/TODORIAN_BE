package com.todorian.todo.service;

import com.todorian.todo.domain.Todo;
import com.todorian.todo.domain.TodoCreateRequestDTO;
import com.todorian.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void createTodo(TodoCreateRequestDTO todoCreateRequestDTO) {
        Todo todo = Todo.builder()
                .todoContent(todoCreateRequestDTO.getTodoContent())
                .todoCreatedAt(todoCreateRequestDTO.getTodoCreatedAt())
                .build();

        todoRepository.save(todo);
    }
}

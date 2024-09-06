package com.todorian.todo.application.service;

import com.todorian.todo.application.dto.TodoCreateRequestDTO;
import com.todorian.todo.domain.model.Todo;
import com.todorian.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void createTodo(TodoCreateRequestDTO todoCreateRequestDTO) {
        Todo todo = Todo.builder()
                .todoContent(todoCreateRequestDTO.getTodoContent())
                .build();

        todoRepository.save(todo);
    }

    public Optional<Todo> findTodoById(Long todoId) {
        return todoRepository.findById(todoId);
    }

    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}

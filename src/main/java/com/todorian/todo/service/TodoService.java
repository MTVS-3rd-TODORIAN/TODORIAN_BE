package com.todorian.todo.service;

import com.todorian.todo.domain.Todo;
import com.todorian.todo.dto.TodoCreateRequestDTO;
import com.todorian.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .build();

        todoRepository.save(todo);
    }

    public Todo findTodoById(Long todoId) {
        return todoRepository.findById(todoId);
    }

    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}

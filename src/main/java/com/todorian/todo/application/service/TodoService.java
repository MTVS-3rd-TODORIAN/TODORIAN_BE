package com.todorian.todo.application.service;

import com.todorian.todo.domain.model.Todo;
import com.todorian.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private List<Todo> completedTodos = new ArrayList<>();

    // 할 일 완료 시 임시 저장
    public void completeTodo(Todo todo) {
        completedTodos.add(todo);
        System.out.println("할 일이 완료되었습니다: " + todo.getMemberId());
    }

    // 임시 저장된 할 일 목록을 반환
    public List<Todo> getCompletedTasks() {
        return completedTodos;
    }

    // DB 저장 후 임시 리스트를 비움
    public void clearCompletedTasks() {
        completedTodos.clear();
    }

    public Optional<Todo> findTodoById(Long todoId) {
        return todoRepository.findById(todoId);
    }

    public List<Todo> findAllTodosByMemberId(Long memberId) {
        return todoRepository.findAllByMemberId(memberId);
    }

    public List<Todo> findAllByMemberIdAndCreateAt(Long memberId, LocalDate selectedDay) {
        return todoRepository.findByCreateAtDateAndMemberId(selectedDay, memberId);
    }

    public void save(Todo requestTodo) {
        Todo todo = Todo.builder()
                .todoContent(requestTodo.getTodoContent())
                .memberId(2L)
                .build();
        todoRepository.save(todo);
    }
}

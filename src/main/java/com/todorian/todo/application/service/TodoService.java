package com.todorian.todo.application.service;

import com.todorian.todo.application.dto.TodoRequestDTO;
import com.todorian.todo.application.dto.TodoResponseDTO;
import com.todorian.todo.domain.model.Todo;
import com.todorian.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
    // 2L 아이디로 할 일 저장(테스트용)
    public void save2L(Todo requestTodo) {
        Todo todo = Todo.builder()
                .todoContent(requestTodo.getTodoContent())
                .memberId(2L)
                .build();
        todoRepository.save(todo);
    }

    // 할 일 저장
    public void save(TodoRequestDTO.saveTodoDTO dto, Long memberId) {
        Todo todo = Todo.builder()
                .todoContent(dto.todoContent())
                .memberId(memberId)
                .completed(false)
                .build();
        todoRepository.save(todo);
    }

    // 주 별 할일 조회
    public List<TodoResponseDTO.weekTodoDTO> getWeekTodoContents(Long memberId, LocalDate day) {
        // input 날짜에 해당하는 주의 월, 일 날짜 데이터
        LocalDateTime startDate = day.with(DayOfWeek.MONDAY).atStartOfDay();
        LocalDateTime endDate = day.with(DayOfWeek.SUNDAY).atTime(23,59, 59);

        // 값을 가져 올 날짜 범위와 memberId로 할 일 조회
        List<Todo> getTodos = todoRepository.findAllByMemberIdAndCreateAt(memberId, startDate, endDate);

        AtomicInteger idx = new AtomicInteger(1);

        // dto로 반환
        return getTodos.stream().map(t ->
                new TodoResponseDTO.weekTodoDTO(t.getTodoContent(), idx.getAndIncrement())
        ).collect(Collectors.toList());

    }
}

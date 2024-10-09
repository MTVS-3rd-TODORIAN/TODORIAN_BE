package com.todorian.todo.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.todo.application.service.TodoService;
import com.todorian.todo.domain.model.Todo;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todo/{todoId}/complete")
    public ResponseEntity<?> complete(@PathVariable Long todoId){
        Todo todo = todoService.findTodoById(todoId).orElseThrow();
        todo.setCompleted(true);
        todoService.completeTodo(todo);
        return ResponseEntity.ok(ApiUtils.success("할일이 완료되었습니다."));
    }

    // 회원 한 명의 전체 할일 조회
    @GetMapping("/todos/{memberId})")
    public ResponseEntity<?> getTodoList(@PathVariable("memberId") Long memberId) {
        List<Todo> todos = todoService.findAllTodosByMemberId(memberId);
        return ResponseEntity.ok().body(ApiUtils.success(todos));
    }

    // 회원 한 명의 할일 날짜별로 조회
    @GetMapping("/todo/{memberId}/{day}")
    public ResponseEntity<?> getTodoListByDays(@PathVariable("memberId") Long memberId,
                                               @PathVariable("day") String day) {
        LocalDate selectedDay;
        // 메소드 실행 검증 및 날짜 데이터 변경(formatting)
        try {
            selectedDay = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(ApiUtils.error("날짜 형식이 잘못되었습니다."));
        }

        List<Todo> todos = todoService.findAllByMemberIdAndCreateAt(memberId, selectedDay);
        return ResponseEntity.ok().body(ApiUtils.success(todos));
    }
}

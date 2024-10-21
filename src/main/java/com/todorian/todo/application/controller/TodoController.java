package com.todorian.todo.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian._core.utils.SecurityUtils;
import com.todorian.todo.application.service.TodoService;
import com.todorian.todo.domain.model.Todo;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.todorian._core.utils.SecurityUtils.getCurrentMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todo/{todoId}/complete")
    public ResponseEntity<?> complete(@PathVariable("todoId") Long todoId) {
        Todo todo = todoService.findTodoById(todoId).orElseThrow();
        todo.setCompleted(true);
        todoService.completeTodo(todo);
        return ResponseEntity.ok(ApiUtils.success("할일이 완료되었습니다."));
    }

    // 회원 한 명의 전체 할일 조회
    @GetMapping("/todos")
    public ResponseEntity<?> getTodoList() {
        List<Todo> todos = todoService.findAllTodosByMemberId(getCurrentMemberId());
        return ResponseEntity.ok().body(ApiUtils.success(todos));
    }

    // 회원 한 명의 할일 날짜별로 조회
    @GetMapping("/todo/{day}")
    public ResponseEntity<?> getTodoListByDays(@PathVariable("day") String day /* yyyy-MM-dd */) {
        LocalDate selectedDay;
        // 메소드 실행 검증 및 날짜 데이터 변경(formatting)
        try {
            selectedDay = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(ApiUtils.error("날짜 형식이 잘못되었습니다."));
        }

        List<Todo> todos = todoService.findAllByMemberIdAndCreateAt(SecurityUtils.getCurrentMemberId(), selectedDay);
        return ResponseEntity.ok().body(ApiUtils.success(todos));
    }

    // 관리자 아이디로 할 일 저장
    @PostMapping("/todo/save")
    public ResponseEntity<?> save(@RequestBody Todo todo) {
        System.out.println(SecurityUtils.getCurrentMemberId());
        todoService.save(todo);
        return ResponseEntity.ok(ApiUtils.success("할 일이 저장되었습니다."));
    }
}

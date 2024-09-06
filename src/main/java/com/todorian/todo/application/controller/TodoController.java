package com.todorian.todo.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.todo.application.service.TodoService;
import com.todorian.todo.domain.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    // 회원 한 명의 전체 할일 조회
    @GetMapping("/todos")
    public ResponseEntity<?> getTodoList(@RequestParam("memberId") Long memberId) {
        List<Todo> todos = todoService.findAllTodosByMemberId(memberId);
        return ResponseEntity.ok().body(ApiUtils.success(todos));
    }

    // 회원 한 명의 할일 날짜별로 조회
    @GetMapping("/todo-day")
    public ResponseEntity<?> getTodoListByDays(@RequestParam("memberId") Long memberId) {
        // 메소드 실행 검증 및 날짜 데이터 변경(formatting)
        List<Todo> todos = todoService.findAllByMemberIdAndCreateAt(memberId, LocalDateTime.now());
        return ResponseEntity.ok().body(ApiUtils.success(todos));
    }
}

package com.todorian.todo.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.todo.application.service.TodoService;
import com.todorian.todo.domain.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/{todoId}/complete")
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
//
//    // 회원 한 명의 할일 날짜별로 조회
//    @GetMapping("/todo/{day}")
//    public ResponseEntity<?> getTodoListByDays(@RequestParam("memberId") Long memberId,
//                                               @PathVariable("day") String day) {
//        // 메소드 실행 검증 및 날짜 데이터 변경(formatting)
//        List<Todo> todos = todoService.findAllByMemberIdAndCreateAt(memberId, LocalDateTime.now());
//        // 날짜별, 회원별로 조회하는 로직 추가 예정
//        return ResponseEntity.ok().body(ApiUtils.success(todos));
//    }
}

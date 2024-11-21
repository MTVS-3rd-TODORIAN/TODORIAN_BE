package com.todorian.todo.point.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian.todo.point.command.domain.model.TodoPoint;
import com.todorian.todo.point.command.domain.model.TodoPointHistory;
import com.todorian.todo.point.command.domain.model.TodoPointType;
import com.todorian.todo.point.command.domain.model.UsageType;
import com.todorian.todo.point.command.domain.repository.TodoPointCommandRepository;
import com.todorian.todo.point.command.domain.repository.TodoPointHistoryCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TodoPointCommandService {

    private final TodoPointCommandRepository todoPointCommandRepository;
    private final TodoPointHistoryCommandRepository todoPointHistoryCommandRepository;

    /*
        TodoPoint Ratio 변경
     */
    public Integer patchTodoPointRatio(TodoPointType todoPointType, Integer ratio) {

        TodoPoint previousTodoPoint = todoPointCommandRepository.findFirstByTodoPointTypeOrderByCreatedAt(todoPointType)
                .orElseThrow(() -> new Exception400("설정된 행동 포인트 정산 비율이 없습니다."));

        TodoPoint todoPoint = newTodoPoint(previousTodoPoint.getCurrentRatio(), ratio);
        todoPointCommandRepository.save(todoPoint);

        return todoPoint.getCurrentRatio();
    }

    /*
        TodoPoint History 생성
     */
    public void createTodoPointHistory(Long memberId, Long todoPointId, UsageType usageType) {

        TodoPointHistory todoPointHistory = TodoPointHistory.builder()
                .memberId(memberId)
                .todoPointId(todoPointId)
                .usageType(usageType)
                .build();
        todoPointHistoryCommandRepository.save(todoPointHistory);
    }

    // Point 증가 비율 조회 (주)
    public Integer getTodoPointRatio() {
        // a
        Optional<TodoPoint> findTodoPoint =
                todoPointCommandRepository.findFirstByTodoPointTypeOrderByCreatedAt(TodoPointType.TODO);
        return findTodoPoint.orElseThrow().getCurrentRatio();
    }

    // TodoPoint 생성
    protected TodoPoint newTodoPoint(Integer previousRatio, Integer newRatio) {
        return TodoPoint.builder()
                .previousRatio(previousRatio)
                .currentRatio(newRatio)
                .build();
    }


}

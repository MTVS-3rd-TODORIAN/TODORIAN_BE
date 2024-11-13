package com.todorian.todo.point.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian.todo.point.command.domain.model.TodoPoint;
import com.todorian.todo.point.command.domain.repository.TodoPointCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TodoPointCommandService {

    private final TodoPointCommandRepository todoPointCommandRepository;

    /*
        TodoPoint Ratio 변경
     */
    public Integer patchTodoPointRatio(Integer ratio) {

        TodoPoint previousTodoPoint = todoPointCommandRepository.findFirstByOrderByCreatedAt()
                .orElseThrow(() -> new Exception400("설정된 행동 포인트 정산 비율이 없습니다."));

        TodoPoint todoPoint = newTodoPoint(previousTodoPoint.getCurrentRatio(), ratio);
        todoPointCommandRepository.save(todoPoint);

        return todoPoint.getCurrentRatio();
    }

    /*
        TodoPoint History 생성
     */
    public void createTodoPointHistory() {

    }

    // TodoPoint 생성
    protected TodoPoint newTodoPoint(Integer previousRatio, Integer newRatio) {
        return TodoPoint.builder()
                .previousRatio(previousRatio)
                .currentRatio(newRatio)
                .build();
    }
}

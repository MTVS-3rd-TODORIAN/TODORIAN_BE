package com.todorian.todo.point.command.application.service;

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
    public void updateTodoPointRatio() {

    }
}

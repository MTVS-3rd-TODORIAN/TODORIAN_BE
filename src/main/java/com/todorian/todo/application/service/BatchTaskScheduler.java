package com.todorian.todo.application.service;

import com.todorian.todo.domain.model.Todo;
import com.todorian.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchTaskScheduler {
    private final TodoService todoService;
    private final TodoRepository todoRepository;

    // 1분에 한 번씩 스케줄링하여 완료된 할 일을 DB에 저장
    @Scheduled(fixedRate = 60000) // 60000ms = 1분
    public void saveCompletedTasksToDB() {

        System.out.println("BatchTaskScheduler.saveCompletedTasksToDB: TODO 배치 실행");
        log.info("BatchTaskScheduler.saveCompletedTasksToDB: TODO 배치 실행");
        List<Todo> completedTasks = todoService.getCompletedTasks();

        if (!completedTasks.isEmpty()) {
            // DB에 저장
            todoRepository.saveAll(completedTasks);
            log.info("Saving completed tasks to DB");
            System.out.println("DB에 저장 완료");

            // 임시 저장소 clean
            todoService.clearCompletedTasks();
        }
    }


}

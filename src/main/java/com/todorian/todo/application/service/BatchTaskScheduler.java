package com.todorian.todo.application.service;

import com.todorian.todo.domain.model.Todo;
import com.todorian.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchTaskScheduler {
    private final TodoService todoService;
    private final TodoRepository todoRepository;

    // 1분에 한 번씩 스케줄링하여 완료된 할 일을 DB에 저장
    @Scheduled(fixedRate = 60000) // 60000ms = 1분
    public void saveCompletedTasksToDB() {
        List<Todo> completedTasks = todoService.getCompletedTasks();

        if (!completedTasks.isEmpty()) {
            // DB에 저장
            todoRepository.saveAll(completedTasks);
            System.out.println("DB에 저장 완료");

            // 임시 저장소 clean
            todoService.clearCompletedTasks();
        }
    }


}

package com.todorian.todo.application.dto;

public class TodoResponseDTO {
    public record weekTodoDTO(
            String todoContent,
            int idx
    ) {}
}

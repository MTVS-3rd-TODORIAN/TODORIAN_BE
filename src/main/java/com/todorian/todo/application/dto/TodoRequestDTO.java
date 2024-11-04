package com.todorian.todo.application.dto;

public class TodoRequestDTO {
    public record saveTodoDTO(
            String todoContent
    ) {}
}

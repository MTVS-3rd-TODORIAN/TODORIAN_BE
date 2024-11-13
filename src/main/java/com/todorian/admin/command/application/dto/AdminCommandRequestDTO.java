package com.todorian.admin.command.application.dto;


public class AdminCommandRequestDTO {

    public record patchTodoPointRatioDTO(
            Integer ratio
    ) {
    }
}

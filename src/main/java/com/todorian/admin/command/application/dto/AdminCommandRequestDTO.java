package com.todorian.admin.command.application.dto;


public class AdminCommandRequestDTO {

    public record patchTodoPointRatioDTO(
            String todoPointType,
            Integer ratio
    ) {
    }
}

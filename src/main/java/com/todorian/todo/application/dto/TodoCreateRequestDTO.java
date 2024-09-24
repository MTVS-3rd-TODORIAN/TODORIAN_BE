package com.todorian.todo.application.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TodoCreateRequestDTO {

    private String todoContent;

}

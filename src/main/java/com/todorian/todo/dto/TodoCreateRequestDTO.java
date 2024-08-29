package com.todorian.todo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TodoCreateRequestDTO {

    private String todoContent;

}

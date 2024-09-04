package com.todorian.todo.domain;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TodoCreateRequestDTO {

    private String todoContent;

}

package com.todorian.todo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "tbl_todo")
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long todoId;

    @Column(name = "TODO_CONTENT")
    private String todoContent;

    @Column(name = "TODO_CREAT_AT")
    private LocalDate todoCreatedAt;

    @Builder
    public Todo(String todoContent, LocalDate todoCreatedAt) {
        this.todoContent = todoContent;
        this.todoCreatedAt = todoCreatedAt;
    }
}

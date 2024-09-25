package com.todorian.todo.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "tbl_todo")
public class Todo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long todoId;

    @Column(name = "TODO_CONTENT")
    private String todoContent;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Builder
    public Todo(String todoContent) {
        this.todoContent = todoContent;
    }
}

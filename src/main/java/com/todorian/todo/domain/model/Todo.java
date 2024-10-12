package com.todorian.todo.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

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

    private boolean completed;

    @Builder
    public Todo(String todoContent, Long memberId, boolean completed) {
        this.todoContent = todoContent;
        this.memberId = memberId;
        this.completed = completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

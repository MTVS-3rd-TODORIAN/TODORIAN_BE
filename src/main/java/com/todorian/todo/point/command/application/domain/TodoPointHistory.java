package com.todorian.todo.point.command.application.domain;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_todo_point_history")
public class TodoPointHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer previousRatio;
    @Column
    private Integer currentRatio;

    @Builder
    public TodoPointHistory(Integer previousRatio, Integer currentRatio) {
        this.previousRatio = previousRatio;
        this.currentRatio = currentRatio;
    }
}

package com.todorian.todo.point.command.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_todo_point")
public class TodoPoint extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private TodoPointType todoPointType;
    @Column
    private Integer previousRatio;
    @Column
    private Integer currentRatio;

    @Builder
    public TodoPoint(TodoPointType todoPointType, Integer previousRatio, Integer currentRatio) {
        this.todoPointType = todoPointType;
        this.previousRatio = previousRatio;
        this.currentRatio = currentRatio;
    }
}

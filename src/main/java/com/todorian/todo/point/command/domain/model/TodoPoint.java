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
    // TODO: Todo 난이도에 대한 비율 산정 (난이도가 없어서 구현 불가)
    @Setter
    @Column
    private Integer ratio;

    @Builder
    public TodoPoint(Integer ratio) {
        this.ratio = ratio;
    }
}

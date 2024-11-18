package com.todorian.todo.point.command.domain.model;

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
    private Long memberId;
    @Column
    private Long todoPointId;
    @Column
    private UsageType usageType;

    @Builder
    public TodoPointHistory(Long memberId, Long todoPointId, UsageType usageType) {
        this.memberId = memberId;
        this.todoPointId = todoPointId;
        this.usageType = usageType;
    }
}

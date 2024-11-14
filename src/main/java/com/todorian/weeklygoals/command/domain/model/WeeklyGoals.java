package com.todorian.weeklygoals.command.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_weekly_goals")
public class WeeklyGoals extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekly_goals_id")
    private Long id;

    private String content;
    private int week;
    private boolean completed;
    private Long memberId;

    @Builder
    public WeeklyGoals(String content, int week, boolean completed, Long memberId) {
        this.content = content;
        this.week = week;
        this.completed = completed;
        this.memberId = memberId;
    }
}

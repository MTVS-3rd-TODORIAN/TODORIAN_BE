package com.todorian.point.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "tbl_todo")
public class Point extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINT_ID")
    private Long pointId;

    @Column(name = "TODO_CONTENT")
    private String pointContent;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Builder
    public Point(String pointContent, Long memberId) {
        this.pointContent = pointContent;
        this.memberId = memberId;
    }
}

package com.todorian.point.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "tbl_point")
public class Point extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINT_ID")
    private Long pointId;

    @Column(name = "POINT_QUANTITY")
    private Integer pointQuantity;

    @Builder
    public Point(Integer pointQuantity) {
        this.pointQuantity = pointQuantity;
    }
}

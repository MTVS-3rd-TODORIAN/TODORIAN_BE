package com.todorian.point.domain.repository;

import com.todorian.point.domain.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}

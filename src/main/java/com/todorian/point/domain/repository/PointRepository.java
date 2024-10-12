package com.todorian.point.domain.repository;

import com.todorian.point.domain.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {

    List<Point> findAllByMemberId(Long memberId);
}

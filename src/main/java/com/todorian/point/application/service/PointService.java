package com.todorian.point.application.service;

import com.todorian.point.application.dto.PointRequestDTO;
import com.todorian.point.domain.model.Point;
import com.todorian.point.domain.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PointService {

    private final PointRepository pointRepository;

    public void AddPointContent(PointRequestDTO.addPointDTO dto) {
        Point point = Point.builder()
                .pointContent(dto.pointContent())
                .memberId(dto.memberId())
                .build();

        pointRepository.save(point);
    }

    public List<Point> GetAllPoints() {
        return pointRepository.findAll();
    }

    public List<Point> GetPointById(Long memberId) {
        return pointRepository.findAllByMemberId(memberId);
    }

}

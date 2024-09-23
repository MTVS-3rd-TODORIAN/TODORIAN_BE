package com.todorian.point.application.service;

import com.todorian.point.application.dto.PointCreateRequestDTO;
import com.todorian.point.domain.model.Point;
import com.todorian.point.domain.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public void save(Long memberId, PointCreateRequestDTO dto) {
        Point point = Point.builder()
                .pointContent(dto.getContent())
                .memberId(memberId).build();
        pointRepository.save(point);
    }

    public Point findById(Long id) {
        return pointRepository.findById(id).orElseThrow();
    }

    public List<Point> findAll() {
        return pointRepository.findAll();
    }

    public List<Point> findAllByMemberId(Long memberId) {
        return pointRepository.findAllByMemberId(memberId);
    }

    public void delete(Long id) {
        pointRepository.deleteById(id);
    }
}

package com.todorian.point.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.point.application.dto.PointRequestDTO;
import com.todorian.point.application.service.PointService;
import com.todorian.point.domain.model.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PointController {
    public final PointService pointService;

    // 전체 포인트 내역 조회
    @GetMapping("/points")
    public ResponseEntity<?> findAllPoints() {
        List<Point> points = pointService.GetAllPoints();
        return ResponseEntity.ok(ApiUtils.success(points));
    }

    // memberId 로 사용자마다 조회
    @GetMapping("/point/{memberId}")
    public ResponseEntity<?> findPointByMemberId(@PathVariable("memberId") Long memberId) {
        List<Point> points = pointService.GetPointById(memberId);
        return ResponseEntity.ok(ApiUtils.success(points));
    }

    // 포인트 내역 저장
    @PostMapping("/point/save")
    public ResponseEntity<?> savePoint(@RequestBody PointRequestDTO.addPointDTO dto) {
        pointService.AddPointContent(dto);
        return ResponseEntity.ok(ApiUtils.success("포인트 내역이 저장되었습니다."));
    }
}

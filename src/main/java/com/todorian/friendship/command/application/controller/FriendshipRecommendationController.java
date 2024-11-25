package com.todorian.friendship.command.application.controller;

import com.todorian.friendship.command.application.dto.FriendshipDTO;
import com.todorian.friendship.command.application.service.FriendshipRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 친구 추천 컨트롤러
 */
@RestController
@RequestMapping("/api/v1/friends/recommendations")
public class FriendshipRecommendationController {

    private final FriendshipRecommendationService recommendationService;

    public FriendshipRecommendationController(FriendshipRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<FriendshipDTO>> getFriendRecommendations(@PathVariable Long memberId) {
        List<FriendshipDTO> recommendations = recommendationService.getFriendRecommendations(memberId);
        return ResponseEntity.ok(recommendations);
    }
}

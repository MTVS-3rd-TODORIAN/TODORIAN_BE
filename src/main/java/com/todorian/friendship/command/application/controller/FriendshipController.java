package com.todorian.friendship.command.application.controller;

import com.todorian.friendship.command.application.dto.FriendshipDTO;
import com.todorian.friendship.command.application.dto.FriendshipQueryDTO;
import com.todorian.friendship.command.application.service.FriendshipQueryService;
import com.todorian.friendship.command.application.service.FriendshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FriendshipController
 * 친구 요청, 수락, 거절, 검색 및 상태 변경 API를 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/v1/friends")
@Tag(name = "Friendship API", description = "친구 관계 관리 및 요청 처리")
public class FriendshipController {

    private final FriendshipService friendshipService;
    private final FriendshipQueryService friendshipQueryService;

    /**
     * FriendshipController 생성자
     *
     * @param friendshipService      친구 관계의 명령형 로직을 처리하는 서비스
     * @param friendshipQueryService 친구 관계의 조회 로직을 처리하는 서비스
     */
    public FriendshipController(FriendshipService friendshipService,
                                FriendshipQueryService friendshipQueryService) {
        this.friendshipService = friendshipService;
        this.friendshipQueryService = friendshipQueryService;
    }

    // *** 명령형 API ***

    @Operation(summary = "친구 요청 생성", description = "요청자와 수신자 간의 친구 요청을 생성합니다.")
    @PostMapping("/requests")
    public ResponseEntity<Void> createFriendRequest(@RequestBody FriendshipDTO dto) {
        friendshipService.createFriendRequest(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "친구 요청 수락", description = "특정 친구 요청을 수락하고 ACTIVE 상태로 변경합니다.")
    @PatchMapping("/requests/{id}/accept")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable("id") Long id) {
        friendshipService.acceptFriendRequest(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "친구 요청 거절", description = "특정 친구 요청을 거절하고 관계를 삭제합니다.")
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<Void> rejectFriendRequest(@PathVariable("id") Long id) {
        friendshipService.rejectFriendRequest(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "친구 요청 취소", description = "보낸 친구 요청을 취소합니다. PENDING 상태에서만 취소 가능합니다.")
    @DeleteMapping("/requests/{id}/cancel")
    public ResponseEntity<Void> cancelFriendRequest(@PathVariable("id") Long id) {
        friendshipService.cancelFriendRequest(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "친구 차단", description = "특정 친구 관계를 BLOCKED 상태로 변경합니다.")
    @PatchMapping("/{id}/block")
    public ResponseEntity<Void> blockFriendship(@PathVariable("id") Long id) {
        friendshipService.blockFriendship(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "친구 관계 복구", description = "특정 친구 관계를 ACTIVE 상태로 복구합니다.")
    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restoreFriendship(@PathVariable("id") Long id) {
        friendshipService.restoreFriendship(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "친구 삭제", description = "특정 친구 관계를 삭제(REMOVED 상태로 변경)합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable("id") Long id) {
        friendshipService.deleteFriendship(id);
        return ResponseEntity.ok().build();
    }

    // *** 조회형 API ***

    @Operation(summary = "친구 요청 목록 조회", description = "특정 사용자가 받은 친구 요청 목록(PENDING 상태)을 조회합니다.")
    @GetMapping("/requests/pending")
    public ResponseEntity<List<FriendshipQueryDTO>> getPendingFriendRequests(@RequestParam Long memberId) {
        return ResponseEntity.ok(friendshipQueryService.getPendingFriendRequests(memberId));
    }

    @Operation(summary = "친구 목록 조회", description = "특정 사용자의 ACTIVE 상태 친구 목록을 조회합니다.")
    @GetMapping("/active")
    public ResponseEntity<List<FriendshipQueryDTO>> getActiveFriendships(@RequestParam Long memberId) {
        return ResponseEntity.ok(friendshipQueryService.getActiveFriendships(memberId));
    }

    @Operation(summary = "닉네임으로 친구 검색", description = "특정 닉네임을 기반으로 친구를 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<FriendshipQueryDTO>> searchFriendsByNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(friendshipQueryService.searchFriendsByNickname(nickname));
    }

    @Operation(summary = "특정 상태별 친구 목록 조회", description = "특정 상태(ACTIVE, BLOCKED, REMOVED)의 친구 목록을 조회합니다.")
    @GetMapping("/status")
    public ResponseEntity<List<FriendshipQueryDTO>> getFriendshipsByStatus(@RequestParam Long memberId,
                                                                           @RequestParam String status) {
        return ResponseEntity.ok(friendshipQueryService.getFriendshipsByStatus(memberId, status));
    }
}

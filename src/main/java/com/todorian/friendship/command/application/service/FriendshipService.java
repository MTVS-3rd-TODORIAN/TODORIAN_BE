package com.todorian.friendship.command.application.service;

import com.todorian.friendship.command.application.dto.FriendshipDTO;
import com.todorian.friendship.command.domain.model.Friendship;
import com.todorian.friendship.command.domain.model.FriendshipStatus;
import com.todorian.friendship.command.domain.repository.FriendshipRepository;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * FriendshipService
 * 친구 관계에 대한 주요 비즈니스 로직을 처리하는 서비스
 */
@Service
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final MemberRepository memberRepository;

    public FriendshipService(FriendshipRepository friendshipRepository,
                             MemberRepository memberRepository) {
        this.friendshipRepository = friendshipRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void createFriendRequest(FriendshipDTO dto) {
        // 요청자 및 수신자를 데이터베이스에서 조회
        Member requester = memberRepository.findById(dto.getRequesterId())
                .orElseThrow(() -> new IllegalArgumentException("요청자가 존재하지 않습니다."));
        Member receiver = memberRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("수신자가 존재하지 않습니다."));

        // PENDING 상태의 친구 요청 생성
        Friendship friendship = Friendship.builder()
                .requester(requester)
                .receiver(receiver)
                .status(FriendshipStatus.PENDING)
                .build();

        friendshipRepository.save(friendship);
    }

    @Transactional
    public void acceptFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청이 존재하지 않습니다."));
        friendship.accept();
    }

    @Transactional
    public void rejectFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청이 존재하지 않습니다."));
        friendship.reject();
    }

    @Transactional
    public void cancelFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청이 존재하지 않습니다."));
        if (friendship.getStatus() != FriendshipStatus.PENDING) {
            throw new IllegalStateException("이미 처리된 친구 요청은 취소할 수 없습니다.");
        }
        friendshipRepository.delete(friendship);
    }

    /**
     * 친구 관계 차단 (BLOCKED 상태로 변경)
     *
     * @param friendshipId 친구 관계 ID
     */
    @Transactional
    public void blockFriendship(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("친구 관계가 존재하지 않습니다."));
        friendship.block(); // 상태를 BLOCKED로 변경
    }

    /**
     * 친구 관계 복구 (ACTIVE 상태로 변경)
     *
     * @param friendshipId 친구 관계 ID
     */
    @Transactional
    public void restoreFriendship(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("친구 관계가 존재하지 않습니다."));
        friendship.accept(); // 상태를 ACTIVE로 변경
    }

    /**
     * 친구 삭제 (REMOVE 상태로 변경)
     *
     * @param friendshipId 친구 관계 ID
     */
    @Transactional
    public void deleteFriendship(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("친구 관계가 존재하지 않습니다."));
        friendship.reject(); // 상태를 REMOVED로 변경
    }
}

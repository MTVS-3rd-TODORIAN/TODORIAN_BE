package com.todorian.friendship.command.application.service;

import com.todorian.friendship.command.application.dto.FriendshipQueryDTO;
import com.todorian.friendship.command.domain.model.Friendship;
import com.todorian.friendship.command.domain.model.FriendshipStatus;
import com.todorian.friendship.command.domain.repository.FriendshipQueryRepository;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * FriendshipQueryService
 * 친구 관계에 대한 읽기 전용 비즈니스 로직을 처리하는 서비스
 */
@Service
public class FriendshipQueryService {

    private final FriendshipQueryRepository friendshipQueryRepository;
    private final MemberRepository memberRepository;
    private final EntityManager entityManager;

    public FriendshipQueryService(FriendshipQueryRepository friendshipQueryRepository,
                                  MemberRepository memberRepository,
                                  EntityManager entityManager) {
        this.friendshipQueryRepository = friendshipQueryRepository;
        this.memberRepository = memberRepository;
        this.entityManager = entityManager;
    }

    /**
     * PENDING 상태의 친구 요청 목록 조회
     *
     * @param memberId 요청받는 사용자 ID
     * @return PENDING 상태의 친구 요청 목록
     */
    public List<FriendshipQueryDTO> getPendingFriendRequests(Long memberId) {
        Member receiver = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다."));

        return friendshipQueryRepository.findByRequesterOrReceiver(receiver, receiver)
                .stream()
                .filter(friendship -> friendship.getStatus() == FriendshipStatus.PENDING)
                .map(friendship -> mapToQueryDTO(friendship))
                .collect(Collectors.toList());
    }

    /**
     * ACTIVE 상태의 친구 관계 목록 조회
     *
     * @param memberId 사용자 ID
     * @return ACTIVE 상태의 친구 관계 목록
     */
    public List<FriendshipQueryDTO> getActiveFriendships(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다."));

        return friendshipQueryRepository.findByRequesterOrReceiver(member, member)
                .stream()
                .filter(friendship -> friendship.getStatus() == FriendshipStatus.ACTIVE)
                .map(friendship -> mapToQueryDTO(friendship))
                .collect(Collectors.toList());
    }

    /**
     * 특정 상태의 친구 관계 목록 조회
     *
     * @param memberId 사용자 ID
     * @param status   조회할 상태
     * @return 해당 상태의 친구 관계 목록
     */
    public List<FriendshipQueryDTO> getFriendshipsByStatus(Long memberId, String status) {
        FriendshipStatus friendshipStatus;
        try {
            friendshipStatus = FriendshipStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 상태 값입니다: " + status);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다."));

        return friendshipQueryRepository.findByRequesterOrReceiver(member, member)
                .stream()
                .filter(friendship -> friendship.getStatus() == friendshipStatus)
                .map(friendship -> mapToQueryDTO(friendship))
                .collect(Collectors.toList());
    }

    /**
     * 닉네임으로 친구 검색
     *
     * @param nickname 검색할 닉네임
     * @return 검색된 친구 관계 목록
     */
    public List<FriendshipQueryDTO> searchFriendsByNickname(String nickname) {
        // JPQL을 사용하여 닉네임으로 회원 검색
        String jpql = "SELECT m FROM Member m WHERE LOWER(m.nickName) LIKE LOWER(:nickname)";
        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        query.setParameter("nickname", "%" + nickname + "%");
        List<Member> members = query.getResultList();

        // 검색된 회원으로 친구 관계 조회
        return members.stream()
                .flatMap(member -> friendshipQueryRepository.findByRequesterOrReceiver(member, member).stream())
                .map(friendship -> mapToQueryDTO(friendship))
                .collect(Collectors.toList());
    }

    /**
     * Friendship 엔티티를 DTO로 매핑
     *
     * @param friendship Friendship 엔티티
     * @return FriendshipQueryDTO
     */
    private FriendshipQueryDTO mapToQueryDTO(Friendship friendship) {
        return FriendshipQueryDTO.builder()
                .id(friendship.getId())
                .requester(friendship.getRequester().getNickName())
                .receiver(friendship.getReceiver().getNickName())
                .status(friendship.getStatus().name())
                .build();
    }
}

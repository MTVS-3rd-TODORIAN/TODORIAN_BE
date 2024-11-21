package com.todorian.friendship.command.application.service;

import com.todorian.friendship.command.application.dto.FriendshipDTO;
import com.todorian.friendship.command.domain.model.Friendship;
import com.todorian.friendship.command.domain.model.FriendshipStatus;
import com.todorian.friendship.command.domain.repository.FriendshipRepository;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 친구 추천 서비스
 */
@Service
public class FriendshipRecommendationService {

    private final FriendshipRepository friendshipRepository;
    private final MemberRepository memberRepository;

    public FriendshipRecommendationService(FriendshipRepository friendshipRepository,
                                           MemberRepository memberRepository) {
        this.friendshipRepository = friendshipRepository;
        this.memberRepository = memberRepository;
    }

    public List<FriendshipDTO> getFriendRecommendations(Long memberId) {
        // 데이터베이스에서 Member 조회
        Member currentMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID: " + memberId));

        // ACTIVE 상태의 친구 관계를 조회
        List<Friendship> activeFriends = friendshipRepository.findByRequesterOrReceiver(currentMember, currentMember)
                .stream()
                .filter(friendship -> friendship.getStatus() == FriendshipStatus.ACTIVE)
                .collect(Collectors.toList());

        // ACTIVE 친구들의 친구 목록 조회
        Set<Member> friendsOfFriends = activeFriends.stream()
                .flatMap(friendship -> Stream.of(friendship.getRequester(), friendship.getReceiver()))
                .filter(friend -> !friend.getId().equals(memberId)) // 본인 제외
                .collect(Collectors.toSet());

        // 현재 사용자의 기존 친구 목록
        Set<Member> existingFriends = activeFriends.stream()
                .flatMap(friendship -> Stream.of(friendship.getRequester(), friendship.getReceiver()))
                .collect(Collectors.toSet());

        // 기존 친구를 추천 목록에서 제외
        friendsOfFriends.removeAll(existingFriends);

        // DTO 변환
        return friendsOfFriends.stream()
                .map(friend -> FriendshipDTO.builder()
                        .requesterId(friend.getId())
                        .receiverId(null)
                        .status("RECOMMENDED")
                        .build())
                .collect(Collectors.toList());
    }
}

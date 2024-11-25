package com.todorian.friendship.command.domain.repository;

import com.todorian.friendship.command.domain.model.Friendship;
import com.todorian.member.command.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipQueryRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByRequesterOrReceiver(Member requester, Member receiver);
}

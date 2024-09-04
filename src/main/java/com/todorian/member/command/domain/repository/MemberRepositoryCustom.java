package com.todorian.member.command.domain.repository;

import com.todorian.member.command.domain.model.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);
}

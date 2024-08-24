package com.todorian.member.repository;

import com.todorian.member.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);
}

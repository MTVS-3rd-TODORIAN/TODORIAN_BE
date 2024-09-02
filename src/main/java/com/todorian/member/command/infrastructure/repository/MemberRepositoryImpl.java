package com.todorian.member.command.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.model.QMember;
import com.todorian.member.command.domain.repository.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findByEmail(String email) {

        QMember member = QMember.member;

        Member foundMember = jpaQueryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(foundMember);
    }
}
package com.todorian.membercharacter.command.domain.repository;

import com.todorian.item.domain.model.Item;
import com.todorian.membercharacter.command.application.dto.MemberCharacterFindResponseDTO;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MemberCharacterRepository extends JpaRepository<MemberCharacter, Long> {

    @Query("SELECT m.characterId, m.step, m.usedGrowthPoint, m.growthPoint, m.completedStatus, c.characterImgsrc " +
            "FROM Character c LEFT JOIN MemberCharacter m ON c.characterId = m.characterId " +
            "WHERE m.memberId = :memberId")
    List<Object[]> findMemberCharactersByMemberId(Long memberId);

    @Query("SELECT m.characterId, m.step, m.usedGrowthPoint, m.growthPoint, m.completedStatus, c.characterImgsrc " +
            "FROM Character c LEFT JOIN MemberCharacter m ON c.characterId = m.characterId " +
            "WHERE m.memberId = :memberId AND m.completedStatus = false")
    Object[] findCurrentMemberCharacterByMemberId(Long memberId);
}

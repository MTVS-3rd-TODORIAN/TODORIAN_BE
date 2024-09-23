package com.todorian.membercharacter.command.domain.repository;

import com.todorian.item.domain.model.Item;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCharacterRepository extends JpaRepository<MemberCharacter, Long> {
}

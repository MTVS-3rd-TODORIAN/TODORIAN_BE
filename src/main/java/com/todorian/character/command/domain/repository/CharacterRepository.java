package com.todorian.character.command.domain.repository;

import com.todorian.character.command.domain.model.Character;
import com.todorian.item.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {


}

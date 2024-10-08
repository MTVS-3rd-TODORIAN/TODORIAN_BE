package com.todorian.membercharacter.command.application.service;

import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberCharacterDeleteService {

    private MemberCharacterRepository memberCharacterRepository;

    @Autowired
    public MemberCharacterDeleteService(MemberCharacterRepository memberCharacterRepository) {
        this.memberCharacterRepository = memberCharacterRepository;
    }

    @Transactional
    public void deleteMemberCharacterById(long memberCharacterId) {
        memberCharacterRepository.deleteById(memberCharacterId);
    }
}

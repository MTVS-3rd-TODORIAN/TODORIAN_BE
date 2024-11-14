package com.todorian.membercharacter.command.application.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian._core.utils.SecurityUtils;
import com.todorian.membercharacter.command.application.dto.MemberCharacterCreateRequestDTO;
import com.todorian.membercharacter.command.application.dto.MemberCharacterFindResponseDTO;
import com.todorian.membercharacter.command.application.dto.MemberCharacterUpdateRequestDTO;
import com.todorian.membercharacter.command.application.service.MemberCharacterCreateService;
import com.todorian.membercharacter.command.application.service.MemberCharacterDeleteService;
import com.todorian.membercharacter.command.application.service.MemberCharacterFindService;
import com.todorian.membercharacter.command.application.service.MemberCharacterUpdateService;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberCharacterController {

    private MemberCharacterFindService memberCharacterFindService;
    private MemberCharacterCreateService memberCharacterCreateService;
    private MemberCharacterUpdateService memberCharacterUpdateService;
    private MemberCharacterDeleteService memberCharacterDeleteService;

    @Autowired
    public MemberCharacterController(
            MemberCharacterFindService memberCharacterFindService,
            MemberCharacterCreateService memberCharacterCreateService,
            MemberCharacterUpdateService memberCharacterUpdateService,
            MemberCharacterDeleteService memberCharacterDeleteService
    ){
        this.memberCharacterFindService = memberCharacterFindService;
        this.memberCharacterCreateService = memberCharacterCreateService;
        this.memberCharacterUpdateService = memberCharacterUpdateService;
        this.memberCharacterDeleteService = memberCharacterDeleteService;
    }

//    public List<MemberCharacterFindResponseDTO> findAllMemberCharacters() {
//
//        List<MemberCharacterFindResponseDTO> foundMemberCharacters;
//
//        foundMemberCharacters = memberCharacterFindService.findAllMemberCharacters();
//
//        return foundMemberCharacters;
//    }

    @GetMapping("/member-characters")
    public List<MemberCharacterFindResponseDTO> findMemberCharactersByMemberId() {

        Long memberId = SecurityUtils.getCurrentMemberId();

        List<MemberCharacterFindResponseDTO> foundMemberCharacter;

        foundMemberCharacter
                = memberCharacterFindService.findMemberCharactersByMemberId(memberId);

        return foundMemberCharacter;
    }

    @GetMapping("/member-characters/current-member-character")
    public MemberCharacterFindResponseDTO findCurrentMemberCharacterByMemberId(){

        Long memberId = SecurityUtils.getCurrentMemberId();

        MemberCharacterFindResponseDTO currentMemberCharacter
                = memberCharacterFindService.findCurrentMemberCharacterByMemberId(memberId);

        return currentMemberCharacter;
    }

    @PostMapping("/member-characters")
    public void createMemberCharacter(MemberCharacterCreateRequestDTO newMemberCharacter) {

        memberCharacterCreateService.createMemberCharacter(newMemberCharacter);

    }

    @PatchMapping("/member-characters/{id}")
    public void updateMemberCharacterById(
            @PathVariable long id,
            MemberCharacterUpdateRequestDTO memberCharacterInfo
    ) {

        // memberCharacterUpdateService.updateMemberCharacterById(id, memberCharacterInfo);

    }

    @DeleteMapping("/member-characters/{id}")
    public void deleteMemberCharacterById(@PathVariable long id) {

        memberCharacterDeleteService.deleteMemberCharacterById(id);

    }

    @PutMapping("/member-characters/using-growth-point")
    public void useGrowthPoint(){
        Long memberId = SecurityUtils.getCurrentMemberId();

        MemberCharacter memberCharacter = memberCharacterFindService.findMemberCharacterById(memberId);

        memberCharacterUpdateService.useGrowthPoint(memberCharacter);
    }

    @GetMapping("/member-character/find-one")
    public ResponseEntity<?> findOneMemberCharacterById() {
        MemberCharacter oneMemberCharacter = memberCharacterFindService.findOneMemberCharacter(SecurityUtils.getCurrentMemberId());
        return ResponseEntity.ok(ApiUtils.success(oneMemberCharacter));
    }
}

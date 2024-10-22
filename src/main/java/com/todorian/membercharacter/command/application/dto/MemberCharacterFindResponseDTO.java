package com.todorian.membercharacter.command.application.dto;

import com.todorian.membercharacter.command.domain.model.MemberCharacter;

import java.time.LocalDateTime;
import java.util.Date;

public class MemberCharacterFindResponseDTO {
    private long memberCharacterId;
    private long memberId;
    private long characterId;
    private int growthPoint;
    private LocalDateTime createdAt;
    private LocalDateTime updatedDate;

    public MemberCharacterFindResponseDTO() {}

    public MemberCharacterFindResponseDTO(
            long memberCharacterId,
            long memberId,
            long characterId,
            int growthPoint,
            LocalDateTime createdAt,
            LocalDateTime updatedDate
    ) {
        this.memberCharacterId = memberCharacterId;
        this.memberId = memberId;
        this.characterId = characterId;
        this.growthPoint = growthPoint;
        this.createdAt = createdAt;
        this.updatedDate = updatedDate;
    }

    public MemberCharacterFindResponseDTO(MemberCharacter memberCharacter) {
        this.memberCharacterId = memberCharacter.getMemberCharacterId();
        this.memberId = memberCharacter.getMemberId();
        this.characterId = memberCharacter.getCharacterId();
        this.growthPoint = memberCharacter.getGrowthPoint();
        this.createdAt = memberCharacter.getCreatedAt();
        this.updatedDate = memberCharacter.getUpdatedDate();
    }

    public long getMemberCharacterId() {
        return memberCharacterId;
    }

    public void setMemberCharacterId(long memberCharacterId) {
        this.memberCharacterId = memberCharacterId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public int getGrowthPoint() {
        return growthPoint;
    }

    public void setGrowthPoint(int growthPoint) {
        this.growthPoint = growthPoint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "MemberCharacterFindResponseDTO{" +
                "memberCharacterId=" + memberCharacterId +
                ", memberId=" + memberId +
                ", characterId=" + characterId +
                ", growthPoint=" + growthPoint +
                ", createdAt=" + createdAt +
                ", updatedDate=" + updatedDate +
                '}';
    }
}

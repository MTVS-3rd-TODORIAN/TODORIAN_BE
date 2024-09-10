package com.todorian.membercharacter.command.domain.model;

import java.sql.Timestamp;
import java.util.Date;

public class MemberCharacterFindResponseDTO {
    private long memberCharacterId;
    private long memberId;
    private long characterId;
    private Date acquisitionTime;

    public MemberCharacterFindResponseDTO() {}

    public MemberCharacterFindResponseDTO(long memberId, long characterId, Date acquisitionTime) {
        this.memberId = memberId;
        this.characterId = characterId;
        this.acquisitionTime = acquisitionTime;
    }

    public long getMemberCharacterId() {
        return memberCharacterId;
    }

    public long getMemberId() {
        return memberId;
    }

    public long getCharacterId() {
        return characterId;
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    @Override
    public String toString() {
        return "MemberCharacterFindResponseDTO{" +
                "memberCharacterId=" + memberCharacterId +
                ", memberId=" + memberId +
                ", characterId=" + characterId +
                ", acquisitionTime=" + acquisitionTime +
                '}';
    }
}

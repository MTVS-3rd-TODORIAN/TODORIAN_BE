package com.todorian.membercharacter.command.domain.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="tbl_membercharacter")
public class MemberCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="membercharacter_no")
    private long memberCharacterNo;

    @Column(name="member_id")
    private long memberId;

    @Column(name="character_id")
    private long characterId;

    @Column(name="acquisition_time")
    private Date acquisitionTime;

    public MemberCharacter() {}

    public MemberCharacter(long memberId, long characterId, Date acquisitionTime) {
        this.memberId = memberId;
        this.characterId = characterId;
        this.acquisitionTime = acquisitionTime;
    }

    public long getMemberCharacterNo() {
        return memberCharacterNo;
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
        return "MemberCharacter{" +
                "memberCharacterNo=" + memberCharacterNo +
                ", memberId=" + memberId +
                ", characterId=" + characterId +
                ", acquisitionTime=" + acquisitionTime +
                '}';
    }
}

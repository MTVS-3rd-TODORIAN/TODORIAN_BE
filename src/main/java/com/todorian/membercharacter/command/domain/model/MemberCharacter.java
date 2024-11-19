package com.todorian.membercharacter.command.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="tbl_member_character")
@Setter
@ToString
@AllArgsConstructor
public class MemberCharacter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_character_id")
    private Long memberCharacterId;

    @Column(name="member_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="member_id")
    private Long memberId;

    @Column(name="character_id")
    private Long characterId;

    @Column(name="step")
    private Integer step;

    @Column(name="used_growth_point")
    private Integer usedGrowthPoint;

    @Column(name="growth_point")
    private Integer growthPoint;

    @Column(name="completed_status")
    private Boolean completedStatus;

    public MemberCharacter(long memberId, long characterId, int growthPoint) {
        this.memberId = memberId;
        this.characterId = characterId;
        this.growthPoint = growthPoint;
    }

    @Builder
    public MemberCharacter(long memberId, long characterId) {
        this.memberId = memberId;
        this.characterId = characterId;
        this.step = 0;
        this.usedGrowthPoint = 0;
        this.growthPoint = 0;
    }
}

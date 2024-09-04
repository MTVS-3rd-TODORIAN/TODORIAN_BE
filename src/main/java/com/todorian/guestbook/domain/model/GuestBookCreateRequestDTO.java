package com.todorian.guestbook.domain.model;

import java.time.LocalDateTime;

public class GuestBookCreateRequestDTO {

    private Long memberId;
    private String guestBookTitle;
    private String guestBookContent;
    private Long authorId;
    private LocalDateTime createdAt;

    // 기본 생성자
    public GuestBookCreateRequestDTO() {
    }

    // 모든 필드를 매개변수로 받는 생성자
    public GuestBookCreateRequestDTO(Long memberId, String guestBookTitle, String guestBookContent, Long authorId, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.guestBookTitle = guestBookTitle;
        this.guestBookContent = guestBookContent;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }

    // Getter와 Setter 메서드
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getGuestBookTitle() {
        return guestBookTitle;
    }

    public void setGuestBookTitle(String guestBookTitle) {
        this.guestBookTitle = guestBookTitle;
    }

    public String getGuestBookContent() {
        return guestBookContent;
    }

    public void setGuestBookContent(String guestBookContent) {
        this.guestBookContent = guestBookContent;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

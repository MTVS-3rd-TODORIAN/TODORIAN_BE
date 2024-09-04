package com.todorian.guestbook.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_guest_book")
public class GuestBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_book_seq")
    private Long guestBookSeq;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "guest_book_title", nullable = false, length = 255)
    private String guestBookTitle;

    @Column(name = "guest_book_content", nullable = false, length = 255)
    private String guestBookContent;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    public GuestBook(){

    }

    public GuestBook(Long memberId, String guestBookTitle, String guestBookContent, LocalDateTime createdAt, Long authorId) {
        this.memberId = memberId;
        this.guestBookTitle = guestBookTitle;
        this.guestBookContent = guestBookContent;
        this.createdAt = createdAt;
        this.authorId = authorId;
    }

    public Long getGuestBookSeq() {
        return guestBookSeq;
    }

    public void setGuestBookSeq(Long guestBookSeq) {
        this.guestBookSeq = guestBookSeq;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "GuestBook{" +
                "guestBookSeq=" + guestBookSeq +
                ", memberId=" + memberId +
                ", guestBookTitle='" + guestBookTitle + '\'' +
                ", guestBookContent='" + guestBookContent + '\'' +
                ", createdAt=" + createdAt +
                ", authorId=" + authorId +
                '}';
    }
}

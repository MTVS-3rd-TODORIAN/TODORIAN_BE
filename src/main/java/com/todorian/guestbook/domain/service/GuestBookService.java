package com.todorian.guestbook.domain.service;

import com.todorian.guestbook.domain.model.GuestBook;
import com.todorian.guestbook.domain.model.GuestBookCreateRequestDTO;
import com.todorian.guestbook.domain.repository.GuestBookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;

    // 생성자를 통한 의존성 주입
    public GuestBookService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    // 모든 GuestBook 항목을 반환
    public List<GuestBook> findAll() {
        return guestBookRepository.findAll();
    }

    // ID로 GuestBook 항목을 찾기
    public GuestBook findById(Long id) {
        return guestBookRepository.findById(id).orElse(null);
    }

    // 새로운 GuestBook 항목을 저장
    public GuestBook save(GuestBookCreateRequestDTO guestBookCreateRequestDTO) {
        // GuestBookCreateRequestDTO를 GuestBook 엔티티로 변환
        GuestBook guestBook = convertToEntity(guestBookCreateRequestDTO);

        // 변환된 GuestBook 객체를 저장
        return guestBookRepository.save(guestBook);
    }

    // ID로 GuestBook 항목 삭제
    public void deleteById(Long id) {
        guestBookRepository.deleteById(id);
    }

    // DTO를 엔티티로 변환하는 메서드
    private GuestBook convertToEntity(GuestBookCreateRequestDTO dto) {
        GuestBook guestBook = new GuestBook();
        guestBook.setMemberId(dto.getMemberId());
        guestBook.setGuestBookTitle(dto.getGuestBookTitle());
        guestBook.setGuestBookContent(dto.getGuestBookContent());
        guestBook.setAuthorId(dto.getAuthorId());
        guestBook.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());
        return guestBook;
    }
}

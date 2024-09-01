package com.todorian.guestbook.domain.service;

import com.todorian.guestbook.domain.model.GuestBook;
import com.todorian.guestbook.domain.model.GuestBookCreateRequestDTO;
import com.todorian.guestbook.domain.repository.GuestBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;

    public GuestBookService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    public List<GuestBook> findAll() {
        return guestBookRepository.findAll();
    }

    public GuestBook findById(Long id) {
        return guestBookRepository.findById(id).orElse(null);
    }

    public GuestBook save(GuestBookCreateRequestDTO guestBook) {
        return guestBookRepository.save(guestBook);
    }

    public void deleteById(Long id) {
        guestBookRepository.deleteById(id);
    }
}

package com.todorian.guestbook.domain.repository;

import com.todorian.guestbook.domain.model.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
}

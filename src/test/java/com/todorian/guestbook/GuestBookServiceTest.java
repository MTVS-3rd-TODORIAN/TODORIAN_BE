package com.todorian.guestbook;

import com.todorian.guestbook.domain.model.GuestBookCreateRequestDTO;
import com.todorian.guestbook.domain.service.GuestBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class GuestBookServiceTest {

    @Autowired
    private GuestBookService guestBookService;

    @BeforeEach
    void setUp() {
        GuestBookCreateRequestDTO guestBookRequestDto = new GuestBookCreateRequestDTO(
                1L, "Initial Title", "Initial Content", 1L, LocalDateTime.now()
        );

        guestBookService.save(guestBookRequestDto);
    }

    private static Stream<Arguments> createGuestBook() {
        return Stream.of(
                Arguments.of(1L, "Test Title", "Test Content", 1L, LocalDateTime.now())
        );
    }

    @DisplayName("방명록 추가 테스트")
    @ParameterizedTest
    @MethodSource("createGuestBook")
    void createGuestBook(Long memberId, String title, String content, Long authorId, LocalDateTime createdAt) {
        GuestBookCreateRequestDTO guestBookRequestDto = new GuestBookCreateRequestDTO(memberId, title, content, authorId, createdAt);

        Assertions.assertDoesNotThrow(
                () -> guestBookService.save(guestBookRequestDto)
        );
    }

    @DisplayName("방명록 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1})
    void deleteGuestBook(long id) {
        Assertions.assertDoesNotThrow(
                () -> guestBookService.deleteById(id)
        );
    }
}

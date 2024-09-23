package com.todorian.coin;

import com.todorian.coin.domain.model.CoinReason;
import com.todorian.coin.domain.model.CoinSaveRequestDto;
import com.todorian.coin.domain.service.CoinService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoinTests {

    @Autowired
    private CoinService coinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("coin 내역 생성 테스트")
    @Test
    void testCreateCoin() {
        CoinSaveRequestDto coinSaveRequestDto = CoinSaveRequestDto.builder()
            .memberId(1L)
            .coinDateTime(LocalDateTime.now())
            .coinAmount(100L)
            .coinReason(CoinReason.ADVERTISEMENT_WATCHED)
            .coinForeignId(1L)
            .build();

        Assertions.assertDoesNotThrow(() -> coinService.save(coinSaveRequestDto));
    }

    @DisplayName("coinAmount 가 0일 때 예외 발생 테스트")
    @Test
    void testCreateCoinWithZeroAmount() {
        CoinSaveRequestDto coinSaveRequestDto = CoinSaveRequestDto.builder()
            .memberId(1L)
            .coinDateTime(LocalDateTime.now())
            .coinAmount(0L) // 0이어도 예외가 발생하지 않음.
            .coinReason(CoinReason.ADVERTISEMENT_WATCHED)
            .coinForeignId(1L)
            .build();

        Assertions.assertDoesNotThrow(() -> coinService.save(coinSaveRequestDto));
    }

    @DisplayName("다양한 coinReason 테스트")
    @Test
    void testCreateCoinWithDifferentReasons() {
        for (CoinReason reason : CoinReason.values()) {
            CoinSaveRequestDto coinSaveRequestDto = CoinSaveRequestDto.builder()
                .memberId(1L)
                .coinDateTime(LocalDateTime.now())
                .coinAmount(100L)
                .coinReason(reason)
                .coinForeignId(1L)
                .build();

            Assertions.assertDoesNotThrow(() -> coinService.save(coinSaveRequestDto));
        }
    }

    @DisplayName("미래의 coinDateTime에 대한 테스트")
    @Test
    void testCreateCoinWithFutureDateTime() {
        CoinSaveRequestDto coinSaveRequestDto = CoinSaveRequestDto.builder()
            .memberId(1L)
            .coinDateTime(LocalDateTime.now().plusDays(1)) // 미래 날짜
            .coinAmount(100L)
            .coinReason(CoinReason.ADVERTISEMENT_WATCHED)
            .coinForeignId(1L)
            .build();

        Assertions.assertDoesNotThrow(() -> coinService.save(coinSaveRequestDto));
    }
}

package com.todorian.coin;

import com.todorian.coin.domain.model.CoinCreateRequestDto;
import com.todorian.coin.domain.model.CoinReason;
import com.todorian.coin.domain.service.CoinService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CoinTests {

    private static final Logger log = LoggerFactory.getLogger(CoinTests.class);
    @Autowired
    private CoinService coinService;

    @DisplayName("coin 내역 생성 테스트")
    @Transactional
    @Test
    void testCreateCoin() {

        CoinCreateRequestDto coinCreateRequestDto = new CoinCreateRequestDto(
            LocalDateTime.now(),
            1L,
            null,
            null,
            null,
            null,
            1L,
            10L,
            CoinReason.CHARACTER_GROWTH
        );

        Assertions.assertDoesNotThrow(
            () -> coinService.createCoin(coinCreateRequestDto)
        );

        log.info("coinCreateRequestDto: {}", coinCreateRequestDto);
    }
}
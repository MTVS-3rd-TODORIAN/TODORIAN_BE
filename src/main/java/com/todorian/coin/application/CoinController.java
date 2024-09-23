package com.todorian.coin.application;

import com.todorian.coin.domain.model.CoinFindResponseDto;
import com.todorian.coin.domain.model.CoinSaveRequestDto;
import com.todorian.coin.domain.service.CoinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coin")
public class CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @PostMapping
    ResponseEntity<CoinFindResponseDto> save(CoinSaveRequestDto coinSaveRequestDto) {
        return coinService.save(coinSaveRequestDto);
    }

    @GetMapping("/{memberId}")
    ResponseEntity<List<CoinFindResponseDto>> findByMemberId(
        @PathVariable("memberId") Long memberId) {
        return coinService.findByMemberId(memberId);
    }

    @GetMapping("/{coinId}")
    ResponseEntity<CoinFindResponseDto> findById(@PathVariable("coinId") Long coinId) {
        return coinService.findByCoinId(coinId);
    }
}
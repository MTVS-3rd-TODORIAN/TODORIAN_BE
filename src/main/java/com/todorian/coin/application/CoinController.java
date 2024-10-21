package com.todorian.coin.application;

import com.todorian._core.utils.SecurityUtils;
import com.todorian.coin.domain.model.CoinFindResponseDto;
import com.todorian.coin.domain.model.CoinSaveRequestDto;
import com.todorian.coin.domain.model.CoinSaveResponseDto;
import com.todorian.coin.domain.service.CoinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @PostMapping
    ResponseEntity<CoinSaveResponseDto> save(@RequestBody CoinSaveRequestDto coinSaveRequestDto) {
        coinSaveRequestDto.setMemberId(SecurityUtils.getCurrentMemberId());
        return coinService.save(coinSaveRequestDto);
    }

    @GetMapping
    ResponseEntity<List<CoinFindResponseDto>> findByMemberIdOrderByCoinDateTimeDesc(
        @RequestParam(name = "memberId") Long memberId) {
        return coinService.findByMemberIdOrderByCoinDateTimeDesc(memberId);
    }

    @GetMapping("/{memberId}/totalCoinAmount")
    ResponseEntity<Long> findTotalCoinAmountByMemberId(@PathVariable("memberId") Long memberId) {
        return coinService.findTotalCoinAmountByMemberId(memberId);
    }

    @GetMapping("/{coinId}")
    ResponseEntity<CoinFindResponseDto> findById(@PathVariable("coinId") Long coinId) {
        return coinService.findByCoinId(coinId);
    }
}
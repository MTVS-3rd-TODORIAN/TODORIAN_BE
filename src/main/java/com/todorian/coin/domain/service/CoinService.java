package com.todorian.coin.domain.service;

import com.todorian.coin.domain.model.Coin;
import com.todorian.coin.domain.model.CoinFindResponseDto;
import com.todorian.coin.domain.model.CoinSaveRequestDto;
import com.todorian.coin.domain.repository.CoinRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
public class CoinService {

    private final CoinRepository coinRepository;

    @Autowired
    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    // 1. Create
    @Transactional
    public ResponseEntity<CoinFindResponseDto> save(CoinSaveRequestDto coinSaveRequestDto) {
        Coin coin = Coin.builder()
            .memberId(coinSaveRequestDto.getMemberId())
            .coinDateTime(coinSaveRequestDto.getCoinDateTime())
            .coinAmount(coinSaveRequestDto.getCoinAmount())
            .coinReason(coinSaveRequestDto.getCoinReason())
            .coinForeignId(coinSaveRequestDto.getCoinForeignId())
            .build();
        coinRepository.save(coin);

        CoinFindResponseDto coinFindResponseDto = CoinFindResponseDto.builder()
            .coinId(coin.getCoinId())       // @GeneratedValue 가 있다면 Not Null
            .memberId(coin.getMemberId())
            .coinAmount(coin.getCoinAmount())
            .coinReason(coin.getCoinReason())
            .coinForeignId(coin.getCoinForeignId())
            .build();

        return ResponseEntity.ok(coinFindResponseDto);
    }

    // 2. 한 회원의 코인 내역 전체 조회
    public ResponseEntity<List<CoinFindResponseDto>> findByMemberId(Long memberId) {
        List<Coin> coinList = coinRepository.findByMemberId(memberId);

        if (coinList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CoinFindResponseDto> coinFindResponseDtoList = coinList.stream()
            .map(coin -> CoinFindResponseDto.builder()
                .coinId(coin.getCoinId())
                .memberId(coin.getMemberId())
                .coinAmount(coin.getCoinAmount())
                .coinReason(coin.getCoinReason())
                .coinForeignId(coin.getCoinForeignId())
                .build())
            .collect(Collectors.toList());

        return ResponseEntity.ok(coinFindResponseDtoList);
    }

    // 3. 코인 내역 아이디를 통한 코인 내역 단일 조회
    public ResponseEntity<CoinFindResponseDto> findByCoinId(Long coinId) {
        Coin coin = coinRepository.findById(coinId)
            .orElseThrow(() -> new NotFoundException("Coin not found with id : " + coinId));

        if (coin == null) {
            return ResponseEntity.notFound().build();
        }

        CoinFindResponseDto coinFindResponseDto = CoinFindResponseDto.builder()
            .coinId(coin.getCoinId())
            .memberId(coin.getMemberId())
            .coinDateTime(coin.getCoinDateTime())
            .coinAmount(coin.getCoinAmount())
            .coinReason(coin.getCoinReason())
            .coinForeignId(coin.getCoinForeignId())
            .build();

        return ResponseEntity.ok(coinFindResponseDto);
    }
}
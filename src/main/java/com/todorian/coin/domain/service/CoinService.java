package com.todorian.coin.domain.service;

import com.todorian.coin.domain.model.Coin;
import com.todorian.coin.domain.model.CoinCreateRequestDto;
import com.todorian.coin.domain.model.CoinFindResponseDto;
import com.todorian.coin.domain.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {

    private CoinRepository coinrepository;

    @Autowired
    public CoinService(CoinRepository coinrepository) {
        this.coinrepository = coinrepository;
    }

    public void createCoin(CoinCreateRequestDto coinCreateRequestDto) {
        Coin coin = new Coin(
            coinCreateRequestDto.getCoinDateTime(),
            coinCreateRequestDto.getCharacterId(),
            coinCreateRequestDto.getAdvertisementId(),
            coinCreateRequestDto.getGameId(),
            coinCreateRequestDto.getItemId(),
            coinCreateRequestDto.getGivenMemberId(),
            coinCreateRequestDto.getMemberId(),
            coinCreateRequestDto.getCoinAmount(),
            coinCreateRequestDto.getCoinReason()
        );
        coinrepository.save(coin);
    }

    public CoinFindResponseDto findById(long coinId) {
        return new CoinFindResponseDto(
            coinrepository.findById(coinId).orElseThrow(IllegalArgumentException::new));
    }
}
package com.todorian.coin.domain.repository;

import com.todorian.coin.domain.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

}
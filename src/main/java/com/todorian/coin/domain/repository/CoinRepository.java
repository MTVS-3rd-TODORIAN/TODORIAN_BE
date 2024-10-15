package com.todorian.coin.domain.repository;

import com.todorian.coin.domain.model.Coin;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

    List<Coin> findByMemberId(Long memberId);

    @Query("SELECT SUM(c.coinAmount) FROM Coin c WHERE c.memberId = :memberId")
    Long findTotalCoinAmountByMemberId(@Param("memberId") Long memberId);
}
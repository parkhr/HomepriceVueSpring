package com.side.project.repository;

import com.side.project.model.ApartTrade;
import com.side.project.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeListRepository extends JpaRepository<ApartTrade, Long> {
    @Query(value = "select a from ApartTrade a where a.apartment = :apartment order by a.tradeDay desc ")
    List<ApartTrade> findByApart(@Param("apartment") Apartment apartment);
}

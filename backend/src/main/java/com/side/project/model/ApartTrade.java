package com.side.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class ApartTrade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartTradeId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "apart_id")
    private Apartment apartment;

    private LocalDate tradeDay; // 거래 년, 월, 일
    private int tradeMoney; // 거래금액
    private int deposit; // 보증금
    private int monthlyRent; // 월세

    private Double area; // 전용면적
    private int floor; // 층

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

}

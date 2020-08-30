package com.side.project;

import com.side.project.model.ApartTrade;
import com.side.project.model.Apartment;
import com.side.project.repository.ApartmentRepository;
import com.side.project.repository.TradeListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProjectApplicationTests {

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    TradeListRepository tradeListRepository;

    @Test
    void contextLoads() {
    }

    // 아파트 매매 parse test
    @Test
    void apartmentTradeParseTest() {

    }

    @Test
    void getApartmentAndApartTradeTest() {
//        List<Apartment> apartments = apartmentRepository.findByCode(1111011500L);
//        for (Apartment apartment : apartments){
//            List<ApartTrade> apartTradeList = tradeListRepository.findByApartId(apartment);
//            for (ApartTrade apartTrade : apartTradeList){
//                System.out.println(apartTrade.toString());
//            }
//        }
    }
}

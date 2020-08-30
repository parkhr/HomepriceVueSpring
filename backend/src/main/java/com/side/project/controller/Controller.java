package com.side.project.controller;

import com.side.project.model.ApartTrade;
import com.side.project.model.Apartment;
import com.side.project.model.Bubjungdong;
import com.side.project.repository.ApartmentRepository;
import com.side.project.repository.BubjungdongRepository;
import com.side.project.repository.TradeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    TradeListRepository tradeListRepository;

    @Autowired
    BubjungdongRepository bubjungdongRepository;

    @GetMapping("/trade/list/{a1}/{a2}/{a3}/{name}")
    public List<Object> getApartTradeList(
            @PathVariable("a1") String a1, @PathVariable("a2") String a2, @PathVariable("a3") String a3, @PathVariable("name") String name){
        Bubjungdong bubjungdong = bubjungdongRepository.findByAddress(a1, a2, a3);

        List<Apartment> apartments = apartmentRepository.findByBubjungdong(bubjungdong, name);
        List<Object> list = new ArrayList<>();

        for (Apartment a : apartments) {
            list.add(tradeListRepository.findByApart(a));
        }
        return list;
    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
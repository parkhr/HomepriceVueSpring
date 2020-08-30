package com.side.project.repository;

import com.side.project.model.Apartment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class ApartmentRepositoryTest {
    @Autowired
    ApartmentRepository apartmentRepository;

    @Test
    public void testApartment(){
//        Apartment apartment = apartmentRepository.findApartmentByCodeAndNameAndArchi(1111011500L, "광화문풍림스페이스본(101동~105동)", "2008");
//        Assertions.assertThat(apartment.getApartId()).isEqualTo(1);
//        Assertions.assertThat(apartment.getName()).isEqualTo("광화문풍림스페이스본(101동~105동)");
//        Assertions.assertThat(apartment.getCode()).isEqualTo(1111011500L);
//        Assertions.assertThat(apartment.getArchi()).isEqualTo("2008");
    }
}
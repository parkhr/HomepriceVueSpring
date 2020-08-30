package com.side.project.repository;

import com.side.project.model.Bubjungdong;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class BubjungdongRepositoryTest {
    @Autowired
    BubjungdongRepository bubjungdongRepository;

    @Test
    public void testBubjungdong(){
        Bubjungdong bubjungdong = new Bubjungdong();
        bubjungdong.setCode(4L);
        bubjungdong.setA1("2");
        bubjungdong.setA2("2");

        bubjungdongRepository.save(bubjungdong);

        Bubjungdong findBub = bubjungdongRepository.findById(4L).get();
        Assertions.assertThat(bubjungdong.getCode()).isEqualTo(findBub.getCode());
        Assertions.assertThat(bubjungdong.getA1()).isEqualTo(findBub.getA1());
        Assertions.assertThat(bubjungdong.getA2()).isEqualTo(findBub.getA2());
        Assertions.assertThat(bubjungdong.getA3()).isEqualTo(findBub.getA3());
        Assertions.assertThat(bubjungdong.getA4()).isEqualTo(findBub.getA4());
    }
}
package com.side.project.repository;

import com.side.project.model.ApartTrade;
import com.side.project.model.Apartment;
import com.side.project.model.Bubjungdong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value = "select a from Apartment a where a.name = :name")
    Apartment findByName(@Param("name") String name);

    @Query(value = "select a from Apartment a where a.bubjungdong = :bubjungdong and a.name like CONCAT('%',:name,'%')")
    List<Apartment> findByBubjungdong(@Param("bubjungdong") Bubjungdong bubjungdong, @Param("name") String name);
}

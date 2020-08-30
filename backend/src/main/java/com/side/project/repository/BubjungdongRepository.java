package com.side.project.repository;

import com.side.project.model.Bubjungdong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BubjungdongRepository extends JpaRepository<Bubjungdong, Long> {
    @Query("select b from Bubjungdong b where b.a1 like CONCAT('%',:a1,'%') and b.a2 like CONCAT('%',:a2,'%') and b.a3 like CONCAT('%',:a3,'%')")
    Bubjungdong findByAddress(@Param("a1") String a1, @Param("a2") String a2, @Param("a3") String a3);
}

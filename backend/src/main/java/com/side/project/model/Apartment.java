package com.side.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Apartment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "code")
    private Bubjungdong bubjungdong; // 법정동 본번코드 + 법정동 부번코드
    private String name; // 아파트 이름
    private String archi; // 건축년도
}

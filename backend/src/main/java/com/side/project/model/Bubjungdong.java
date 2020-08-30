package com.side.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Bubjungdong {
    @Id
    private Long code;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
}

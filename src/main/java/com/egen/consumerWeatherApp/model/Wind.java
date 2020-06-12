package com.egen.consumerWeatherApp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Wind {
    @Id
    private String id;
    private double speed;
    private double degree;

    public Wind(){
        this.id= UUID.randomUUID().toString();
    }
}
/*
 "speed": 3.1,
    "degree": 240
 */
package com.egen.consumerWeatherApp.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
public class Weather {
    @Id
    private String id;
    private String city;
    private String description;
    private Double humidity;
    private double pressure;
    private double temperature;
    @OneToOne(cascade=CascadeType.ALL)
    private Wind wind;
    private Timestamp timestamp;

    public Weather(){
        this.id= UUID.randomUUID().toString();
    }
}
/*
{
  "city": "Chicago",
  "description": "scattered clouds",
  "humidity": 33,
  "pressure": 1020,
  "temperature": 25, (>25)
  "wind": {
    "speed": 3.1, (>6)
    "degree": 240
  },
  "timestamp": "2017-02-14T05:48:41.861Z"
}
 */
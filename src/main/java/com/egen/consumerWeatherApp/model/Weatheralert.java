package com.egen.consumerWeatherApp.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Entity
public class Weatheralert {
    @Id
    private String id;
    private String alert;
    @OneToOne(cascade= CascadeType.ALL)
    private Weather weather;

    public Weatheralert(){
        this.id = UUID.randomUUID().toString();
    }
}

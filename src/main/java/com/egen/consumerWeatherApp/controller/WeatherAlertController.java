package com.egen.consumerWeatherApp.controller;


import com.egen.consumerWeatherApp.model.Weather;
import com.egen.consumerWeatherApp.model.Weatheralert;
import com.egen.consumerWeatherApp.service.AlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherAlertController {

    private AlertService alertService;

    public WeatherAlertController(AlertService alertService){
        this.alertService =alertService;
    }

    @PostMapping("/addReading")

    public boolean addWeatherReadings(@RequestBody Weatheralert weatheralert)  {
            alertService.addAlerts(weatheralert);
            return true;
    }

    @GetMapping("/geAlerts")
    public List<Weatheralert> getAll(){
        return alertService.getAllAlerts();
    }

}

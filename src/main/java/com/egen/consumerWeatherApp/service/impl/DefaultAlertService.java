package com.egen.consumerWeatherApp.service.impl;

import com.egen.consumerWeatherApp.model.Weatheralert;
import com.egen.consumerWeatherApp.repo.AlertRepo;
import com.egen.consumerWeatherApp.service.AlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAlertService implements AlertService {
    private AlertRepo alertRepo;

    public  DefaultAlertService(AlertRepo alertRepo){
        this.alertRepo = alertRepo;
    }
    @Override
    public boolean addAlerts(Weatheralert weatheralert) {
        alertRepo.save(weatheralert);
        return true;
    }

    @Override
    public List<Weatheralert> getAllAlerts() {
        List<Weatheralert> weatheralertList =  alertRepo.findAll();
        return weatheralertList;
    }
}

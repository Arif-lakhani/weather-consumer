package com.egen.consumerWeatherApp.service;

import com.egen.consumerWeatherApp.model.Weatheralert;

import java.util.List;

public interface AlertService {
    boolean addAlerts(Weatheralert weatheralert);
    List<Weatheralert> getAllAlerts();
}

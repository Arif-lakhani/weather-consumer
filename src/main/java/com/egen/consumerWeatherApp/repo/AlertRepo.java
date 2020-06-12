package com.egen.consumerWeatherApp.repo;

import com.egen.consumerWeatherApp.model.Weatheralert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public interface AlertRepo extends JpaRepository<Weatheralert, String> {

}

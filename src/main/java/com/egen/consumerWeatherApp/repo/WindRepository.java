package com.egen.consumerWeatherApp.repo;

import com.egen.consumerWeatherApp.model.Wind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindRepository extends JpaRepository<Wind,String> {
}

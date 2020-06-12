package com.egen.consumerWeatherApp;

import com.egen.consumerWeatherApp.aws.WeatherAlertsListenerSqs;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConsumerWeatherAppApplication {

	public static void main(String[] args) throws JsonProcessingException {
		ApplicationContext applicationContext = SpringApplication.run(ConsumerWeatherAppApplication.class, args);
		WeatherAlertsListenerSqs weatherAlertsListenerSqs = applicationContext.getBean(WeatherAlertsListenerSqs.class);
		weatherAlertsListenerSqs.startListeningToMessages();
	}

}

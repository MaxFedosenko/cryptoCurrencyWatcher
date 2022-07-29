package com.idf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class CryptoCurrencyWatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoCurrencyWatcherApplication.class, args);
	}

}

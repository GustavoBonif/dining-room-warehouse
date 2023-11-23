package com.diningroom.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DiningRoomWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiningRoomWarehouseApplication.class, args);
	}

}

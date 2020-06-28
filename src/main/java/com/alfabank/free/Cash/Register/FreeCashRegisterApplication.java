package com.alfabank.free.Cash.Register;

import controller.CashRestController;
import entity.AtmOffice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.AtmOfficeRepository;
import service.AtmOfficeService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"entity"})
@ComponentScan(basePackages = {"service", "controller"})
public class FreeCashRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreeCashRegisterApplication.class, args);
	}

}

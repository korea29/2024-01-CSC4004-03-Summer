package com.dgu_csc.akomanager_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.repository.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

@SpringBootApplication
public class AkoManagerBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(AkoManagerBackApplication.class, args);
	}

}
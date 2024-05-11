package com.dgu_csc.akomanager_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.repository.UserRepository;

import java.time.LocalDate;

@SpringBootApplication
public class AkoManagerBackApplication implements CommandLineRunner {
	UserRepository r1;
	public static void main(String[] args) {
		SpringApplication.run(AkoManagerBackApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		User test1 = new User("test1", "00" , "00", LocalDate.of(2000, 03, 17), "00");
		r1.save(test1);
		
	}
}
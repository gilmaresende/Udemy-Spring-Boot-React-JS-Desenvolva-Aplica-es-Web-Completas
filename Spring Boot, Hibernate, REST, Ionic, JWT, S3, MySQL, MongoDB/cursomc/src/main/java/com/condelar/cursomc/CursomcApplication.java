package com.condelar.cursomc;

import com.condelar.cursomc.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	S3Service s3Service;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, "--debug");
		//SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("C:\\Users\\Gilmar\\Desktop\\tmp\\Corretto-Duke-no-text-1024x536.jpg");

	}

}

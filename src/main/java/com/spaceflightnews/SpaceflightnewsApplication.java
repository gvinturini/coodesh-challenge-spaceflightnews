package com.spaceflightnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpaceflightnewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceflightnewsApplication.class, args);
	}

}

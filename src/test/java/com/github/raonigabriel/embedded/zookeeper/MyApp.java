package com.github.raonigabriel.embedded.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.raonigabriel.embedded.zookeeper.annotation.EnableEmbeddedZooKeeper;

@EnableEmbeddedZooKeeper
@SpringBootApplication
public class MyApp {

	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
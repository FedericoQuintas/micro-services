package de.bonify.news.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration
@EnableDiscoveryClient
public class NewsServer	 {

	public static void main(String[] args) {

		System.setProperty("spring.config.name", "news-server");

		SpringApplication.run(NewsServer.class, args);
	}
}

package de.bonify.news.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(NewsConfiguration.class)
public class NewsServer	 {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("spring.config.name", "news-server");

		SpringApplication.run(NewsServer.class, args);
		

		System.exit(0);
	}
}

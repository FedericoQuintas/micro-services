package de.claudiopoll.poll.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(PollConfiguration.class)
public class PollServer	 {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("spring.config.name", "poll-server");

		SpringApplication.run(PollServer.class, args);

	}
}

package com.steam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaServer
@ComponentScan(basePackages = {"com.steam"})
public class SteamClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteamClassApplication.class, args);
    }

}

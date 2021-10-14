package com.oauth.user.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OAuthUserMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthUserMsApplication.class, args);
	}

}

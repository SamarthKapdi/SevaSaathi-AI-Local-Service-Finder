package com.sevasaathi.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main application class for SevaSaathi Platform.
 * 
 * SevaSaathi is an AI-powered platform that connects users in small towns 
 * and rural areas with local service providers, improving access to trusted 
 * services while creating employment opportunities for skilled workers.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sevasaathi.platform.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.sevasaathi.platform.repository.elasticsearch")
@EnableTransactionManagement
@EnableAsync
public class SevaSaathiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevaSaathiApplication.class, args);
    }
}
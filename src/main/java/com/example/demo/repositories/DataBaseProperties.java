package com.example.demo.repositories;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "DB")
public class DataBaseProperties {
    private  String request;

}

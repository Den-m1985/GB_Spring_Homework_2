package com.example.demo.repositories;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "database")
@Data
public class DataBaseProperties {
    private String findAll;
    private String save;
    private String deleteById;
    private String getOne;
    private String update;

}

package com.carlosasrc.dataanalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class DataanalyserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataanalyserApplication.class, args);
    }

}

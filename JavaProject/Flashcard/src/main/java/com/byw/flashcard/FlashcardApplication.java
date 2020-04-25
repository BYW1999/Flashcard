package com.byw.flashcard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
@MapperScan("com.byw.flashcard.mapper")
public class FlashcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashcardApplication.class, args);
    }

}

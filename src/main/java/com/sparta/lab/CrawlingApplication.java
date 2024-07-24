package com.sparta.lab;

import com.sparta.lab.crawling.service.MusicalCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlingApplication implements CommandLineRunner {

    @Autowired
    private MusicalCrawlerService musicalCrawlerService;

    public static void main(String[] args) {
        SpringApplication.run(CrawlingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        musicalCrawlerService.crawlAndSave(); // 애플리케이션 시작 시 크롤링 실행
    }
}

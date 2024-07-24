package com.sparta.lab.crawling.config;

import com.sparta.lab.crawling.service.MusicalCrawlerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    private final MusicalCrawlerService musicalCrawlerService;

    public SchedulingConfig(MusicalCrawlerService musicalCrawlerService) {
        this.musicalCrawlerService = musicalCrawlerService;
    }

    @Scheduled(cron = "0 0 1 * * ?") // 매일 오전 1시에 실행
    public void performScheduledCrawling() {
        musicalCrawlerService.crawlAndSave();
    }
}

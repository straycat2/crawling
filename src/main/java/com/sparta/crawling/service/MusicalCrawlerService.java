package com.sparta.crawling.service;

import com.sparta.crawling.entity.Musical;
import com.sparta.crawling.repository.MusicalRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MusicalCrawlerService {

    @Autowired
    private MusicalRepository musicalRepository;

    public void crawlAndSave() {
        String url = "https://ticket.interpark.com/"; // 크롤링할 URL

        try {
            // Jsoup을 사용하여 웹 페이지 가져오기
            Document doc = Jsoup.connect(url).get();

            // 필요한 데이터 추출
            Elements events = doc.select("div.event"); // 실제 HTML 구조에 맞게 수정

            for (Element event : events) {
                String title = event.select("h3").text(); // 공연 제목
                String date = event.select("span.date").text(); // 공연 날짜

                Musical musical = new Musical();
                musical.setTitle(title);
                musical.setDate(date);

                // 데이터베이스에 저장
                musicalRepository.save(musical);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

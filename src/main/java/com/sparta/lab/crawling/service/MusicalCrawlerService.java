package com.sparta.lab.crawling.service;

import com.sparta.lab.crawling.entity.Musical;
import com.sparta.lab.crawling.repository.MusicalRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
public class MusicalCrawlerService {

    @Autowired
    private MusicalRepository musicalRepository;

    public void crawlAndSave() {
        String url = "https://ticket.interpark.com/webzine/paper/TPNoticeList_iFrame.asp?bbsno=34&pageno=1&KindOfGoods=TICKET&Genre=1&sort=opendate&stext="; // 크롤링할 URL

        try {
            // URLConnection을 사용하여 인코딩 설정
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", "EUC-KR");
            InputStream inputStream = connection.getInputStream();

            // Jsoup을 사용하여 인코딩 설정
            Document doc = Jsoup.parse(inputStream, "EUC-KR", url);

            // HTML 구조 출력 (디버깅용)
            System.out.println(doc.html());

            // 필요한 데이터 추출
            Elements rows = doc.select("tbody tr"); // 각 공연이 포함된 행을 선택

            for (Element row : rows) {
                // 공연 제목 추출
                Element titleElement = row.select("td.subject a").first(); // <a> 태그 선택
                String title = titleElement != null ? titleElement.text() : ""; // 공연 제목

                // 공연 날짜 추출
                String date = row.select("td.date").text(); // 공연 날짜

                System.out.println("Title: " + title);
                System.out.println("Date: " + date);

                if (title.isEmpty() || date.isEmpty()) {
                    // 데이터가 없는 경우는 무시하거나 로그 출력
                    System.out.println("Missing data for row: " + row.html());
                    continue;
                }

                Musical musical = new Musical();
                musical.setTitle(title);
                musical.setDate(date);

                // 데이터베이스에 저장
                musicalRepository.save(musical);
            }
        } catch (IOException e) {
            // IOException 처리 및 로그 출력
            e.printStackTrace();
        }
    }
}

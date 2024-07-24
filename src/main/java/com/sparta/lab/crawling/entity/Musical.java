package com.sparta.lab.crawling.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Musical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    private String title; // 제목
    private String date; // 날짜

    // 기본 생성자
    public Musical() {}

    // 모든 필드를 인자로 받는 생성자 (선택적)
    public Musical(String title, String date) {
        this.title = title;
        this.date = date;
    }

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Musical{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

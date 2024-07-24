package com.sparta.lab.crawling.repository;

import com.sparta.lab.crawling.entity.Musical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicalRepository extends JpaRepository<Musical, Long> {
    // 추가적인 쿼리 메소드를 정의할 수 있습니다.
}

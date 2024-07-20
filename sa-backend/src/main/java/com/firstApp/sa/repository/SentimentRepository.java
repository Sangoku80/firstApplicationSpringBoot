package com.firstApp.sa.repository;

import com.firstApp.sa.entities.Sentiment;
import com.firstApp.sa.enums.TypeSentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SentimentRepository extends JpaRepository<Sentiment, Integer> {

    List<Sentiment> findByType(TypeSentiment type);
}

package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {
    private final RecommendationRepository repository;

    public List<Recommendation> getUserRecommendation(String userId) {
        return repository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return repository.findByActivityId(activityId)
                .orElseThrow(()-> new RuntimeException("no recommendation found for this activity id: "+activityId));
    }
}

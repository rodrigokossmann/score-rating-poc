package com.poc.scoreratingcalculator.scoreratingcalculator.services;

import com.poc.scoreratingcalculator.scoreratingcalculator.dto.SocialRatingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserScoreService {

  private RedisTemplate<String, Double> redisTemplate;

  @Autowired
  public UserScoreService(
      RedisTemplate<String, Double> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  private final Logger logger = LoggerFactory.getLogger(ScoreConsumer.class);


  public void calculateUserScore(SocialRatingDTO socialRatingDTO) {
    Double score = socialRatingDTO.getSeed() * socialRatingDTO.getAge();

    logger.info("{} {} has {} score", socialRatingDTO.getFirstName(), socialRatingDTO.getLastName(),
        score);

    redisTemplate.opsForValue()
        .set(socialRatingDTO.getFirstName().concat(".").concat(socialRatingDTO.getLastName()).toLowerCase(),
            score);
  }

}

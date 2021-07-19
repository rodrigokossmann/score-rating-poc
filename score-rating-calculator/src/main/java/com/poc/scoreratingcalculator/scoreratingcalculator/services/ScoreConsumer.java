package com.poc.scoreratingcalculator.scoreratingcalculator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.scoreratingcalculator.scoreratingcalculator.dto.SocialRatingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ScoreConsumer {

  private ObjectMapper objectMapper;
  private UserScoreService userScoreService;

  private final Logger logger = LoggerFactory.getLogger(ScoreConsumer.class);

  @Autowired
  public ScoreConsumer(ObjectMapper objectMapper,
      UserScoreService userScoreService) {
    this.objectMapper = objectMapper;
    this.userScoreService = userScoreService;
  }

  @KafkaListener(topics = "${social.rating.topic}", groupId = "group_id")
  public void consume(String message) throws JsonProcessingException {
    SocialRatingDTO socialRatingDTO = objectMapper.readValue(message, SocialRatingDTO.class);
    logger.info("Consumed message -> {}", socialRatingDTO);
    userScoreService.calculateUserScore(socialRatingDTO);
  }

}

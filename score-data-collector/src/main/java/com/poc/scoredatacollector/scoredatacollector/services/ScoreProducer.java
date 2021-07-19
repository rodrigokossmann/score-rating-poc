package com.poc.scoredatacollector.scoredatacollector.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.scoredatacollector.scoredatacollector.dto.SocialRatingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScoreProducer {

  private String topic;

  private Double seed;

  private ObjectMapper objectMapper;

  private static final Logger LOGGER = LoggerFactory.getLogger(ScoreProducer.class);

  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  public ScoreProducer(
      @Value("${social.rating.topic}") String topic,
      @Value("${social.rating.seed}") Double seed,
      ObjectMapper objectMapper,
      KafkaTemplate<String, String> kafkaTemplate) {
    this.topic = topic;
    this.seed = seed;
    this.objectMapper = objectMapper;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(SocialRatingDTO socialRatingDTO) throws JsonProcessingException {
    socialRatingDTO.setSeed(seed);
    LOGGER.info("Sending message {} to kafka", socialRatingDTO);

    String message = objectMapper.writeValueAsString(socialRatingDTO);
    this.kafkaTemplate.send(topic, message);
  }

}

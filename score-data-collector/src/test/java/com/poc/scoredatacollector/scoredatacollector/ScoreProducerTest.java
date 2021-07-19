package com.poc.scoredatacollector.scoredatacollector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.scoredatacollector.scoredatacollector.dto.SocialRatingDTO;
import com.poc.scoredatacollector.scoredatacollector.services.ScoreProducer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

@TestInstance(Lifecycle.PER_CLASS)
public class ScoreProducerTest {

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @Mock
  private ObjectMapper objectMapper;

  private ScoreProducer scoreProducer;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    scoreProducer = new ScoreProducer("social-rating", 1.0, objectMapper, kafkaTemplate);
  }

  @Test
  public void testSendMessage() throws JsonProcessingException {
    SocialRatingDTO messageActual = SocialRatingDTO.builder().firstName("Rodrigo")
        .lastName("Kossmann").age(32).seed(0.0).build();
    SocialRatingDTO messageExpected = SocialRatingDTO.builder().firstName("Rodrigo")
        .lastName("Kossmann").age(32).seed(1.0).build();

    Mockito.when(objectMapper.writeValueAsString(messageExpected)).thenReturn("expected");

    scoreProducer.sendMessage(messageActual);

    Mockito.verify(kafkaTemplate, Mockito.times(1)).send("social-rating", "expected");

  }

}

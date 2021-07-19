package com.poc.scoreratingcalculator.scoreratingcalculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.scoreratingcalculator.scoreratingcalculator.dto.SocialRatingDTO;
import com.poc.scoreratingcalculator.scoreratingcalculator.services.ScoreConsumer;
import com.poc.scoreratingcalculator.scoreratingcalculator.services.UserScoreService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@TestInstance(Lifecycle.PER_CLASS)
public class ScoreConsumerTest {

  @Mock
  private ObjectMapper objectMapper;
  @Mock
  private UserScoreService userScoreService;

  private ScoreConsumer scoreConsumer;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.scoreConsumer = new ScoreConsumer(objectMapper, userScoreService);
  }

  @Test
  public void testConsumeMessage() throws JsonProcessingException {
    String message = "message";
    SocialRatingDTO socialRatingDTO = SocialRatingDTO.builder().firstName("Rodrigo")
        .lastName("Kossmann").age(32).seed(1.0).build();

    Mockito.when(objectMapper.readValue(message, SocialRatingDTO.class))
        .thenReturn(socialRatingDTO);

    scoreConsumer.consume(message);

    Mockito.verify(userScoreService, Mockito.times(1)).calculateUserScore(socialRatingDTO);

  }

}

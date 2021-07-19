package com.poc.scoreratingcalculator.scoreratingcalculator;

import com.poc.scoreratingcalculator.scoreratingcalculator.dto.SocialRatingDTO;
import com.poc.scoreratingcalculator.scoreratingcalculator.services.UserScoreService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@TestInstance(Lifecycle.PER_CLASS)
public class UserScoreServiceTest {

  @Mock
  private RedisTemplate<String, Double> redisTemplate;
  @Mock
  private ValueOperations<String, Double> valueOperations;

  private UserScoreService userScoreService;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.userScoreService = new UserScoreService(redisTemplate);
  }

  @Test
  public void testCalculateUserScore() {
    SocialRatingDTO socialRatingDTO = SocialRatingDTO.builder().firstName("Rodrigo")
        .lastName("Kossmann").age(32).seed(1.0).build();

    Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);

    userScoreService.calculateUserScore(socialRatingDTO);

    Mockito.verify(valueOperations).set("rodrigo.kossmann", 32.0);

  }


}

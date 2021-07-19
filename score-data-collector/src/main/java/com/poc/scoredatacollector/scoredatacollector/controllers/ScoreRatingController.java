package com.poc.scoredatacollector.scoredatacollector.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.scoredatacollector.scoredatacollector.dto.RestMessageDTO;
import com.poc.scoredatacollector.scoredatacollector.dto.SocialRatingDTO;
import com.poc.scoredatacollector.scoredatacollector.services.ScoreProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social-rating")
public class ScoreRatingController {

  @Autowired
  private ScoreProducer scoreProducer;

  private static final Logger LOGGER = LoggerFactory.getLogger(ScoreRatingController.class);

  @PostMapping
  public ResponseEntity calculateSocialRating(@RequestBody SocialRatingDTO socialRatingDTO) {
    try {
      LOGGER.info("Received {}", socialRatingDTO);
      scoreProducer.sendMessage(socialRatingDTO);
      RestMessageDTO restMessageDTO = RestMessageDTO.builder()
          .message("Values accepted to be processed.").build();
      return new ResponseEntity(restMessageDTO, HttpStatus.ACCEPTED);
    } catch (JsonProcessingException e) {
      RestMessageDTO restMessageDTO = RestMessageDTO.builder()
          .message("Values not accepted.").build();
      return new ResponseEntity(restMessageDTO, HttpStatus.NOT_ACCEPTABLE);
    }
  }

}

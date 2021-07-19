package com.poc.scoreratingcalculator.scoreratingcalculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocialRatingDTO implements Serializable {

  @JsonProperty("first_name")
  private String firstName;
  @JsonProperty("last_name")
  private String lastName;
  private Integer age;
  private Double seed;

}

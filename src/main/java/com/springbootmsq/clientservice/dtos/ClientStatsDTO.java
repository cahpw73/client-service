package com.springbootmsq.clientservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
public class ClientStatsDTO {

  private double averageAge;
  private double standardDeviation;

  public ClientStatsDTO() {
  }

  public ClientStatsDTO(double averageAge, double standardDeviation) {
    this.averageAge = averageAge;
    this.standardDeviation = standardDeviation;
  }
}

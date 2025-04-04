package com.springbootmsq.clientservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
public class ClientStatsDTO {
  private double averageAge;
  private double standardDeviation;
}

package com.springbootmsq.clientservice.dtos;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@Builder
public class ClientResponseDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private Integer age;
  private LocalDate birthDate;
  private Integer lifeExpectancy;
}

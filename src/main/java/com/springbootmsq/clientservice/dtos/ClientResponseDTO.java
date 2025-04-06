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

  public ClientResponseDTO() {
  }

  public ClientResponseDTO(Long id, String firstName, String lastName, Integer age, LocalDate birthDate,
      Integer lifeExpectancy) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.birthDate = birthDate;
    this.lifeExpectancy = lifeExpectancy;
  }
}

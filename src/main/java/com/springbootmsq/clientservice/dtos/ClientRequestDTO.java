package com.springbootmsq.clientservice.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

@Getter
@Setter
@Builder
public class ClientRequestDTO {

  @NotBlank(message = "First name is required")
  private String firstName;

  @NotBlank(message = "Last name is required")
  private String lastName;

  @NotNull(message = "Age is required")
  @Min(value = 1, message = "Age must be > 0")
  private Integer age;

  @NotNull(message = "Birth date is required")
  @Past(message = "Birth date must be in the past")
  @Schema(type = "string", format = "date", example = "2000-12-31")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  public ClientRequestDTO() {
  }

  public ClientRequestDTO(String firstName, String lastName, Integer age, LocalDate birthDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.birthDate = birthDate;
  }
}

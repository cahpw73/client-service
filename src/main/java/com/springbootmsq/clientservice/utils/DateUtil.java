package com.springbootmsq.clientservice.utils;

import java.time.LocalDate;

import com.springbootmsq.clientservice.exceptions.BusinessException;

public class DateUtil {

  public static void validateAgeMatchesBirthDate(int age, LocalDate birthDate) {
    LocalDate today = LocalDate.now();
    LocalDate from = today.minusYears(age + 1).plusDays(1);
    LocalDate to = today.minusYears(age);

    if (birthDate.isBefore(from) || birthDate.isAfter(to)) {
      throw new BusinessException("The provided age does not match the birth date.");
    }
  }
}

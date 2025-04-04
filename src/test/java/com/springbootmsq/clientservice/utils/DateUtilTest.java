package com.springbootmsq.clientservice.utils;

import com.springbootmsq.clientservice.exceptions.BusinessException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class DateUtilTest {

    @Test
    void shouldNotThrowWhenAgeMatchesBirthDate() {
        int age = 25;
        LocalDate birthDate = LocalDate.now().minusYears(age);
        assertThatCode(() -> DateUtil.validateAgeMatchesBirthDate(age, birthDate))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldThrowExceptionWhenAgeDoesNotMatchBirthDate_TooYoung() {
        int age = 25;
        LocalDate birthDate = LocalDate.now().minusYears(20); // too young

        assertThatThrownBy(() -> DateUtil.validateAgeMatchesBirthDate(age, birthDate))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("age does not match");
    }

    @Test
    void shouldThrowExceptionWhenAgeDoesNotMatchBirthDate_TooOld() {
        int age = 25;
        LocalDate birthDate = LocalDate.now().minusYears(30); // too old

        assertThatThrownBy(() -> DateUtil.validateAgeMatchesBirthDate(age, birthDate))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("age does not match");
    }
}

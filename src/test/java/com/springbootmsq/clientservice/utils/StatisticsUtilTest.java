package com.springbootmsq.clientservice.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class StatisticsUtilTest {

  @Test
  void shouldCalculateAverageCorrectly() {
    List<Integer> ages = List.of(10, 20, 30);
    double average = StatisticsUtil.calculateAverage(ages);

    assertThat(average).isEqualTo(20.0);
  }

  @Test
  void shouldReturnZeroAverageIfListIsEmpty() {
    double average = StatisticsUtil.calculateAverage(List.of());

    assertThat(average).isEqualTo(0.0);
  }

  @Test
  void shouldCalculateStandardDeviationCorrectly() {
    List<Integer> ages = List.of(10, 20, 30);
    double avg = StatisticsUtil.calculateAverage(ages);
    double stdDev = StatisticsUtil.calculateStandardDeviation(ages, avg);

    assertThat(stdDev).isCloseTo(8.16, within(0.01));
  }

  @Test
  void shouldReturnZeroStandardDeviationIfListIsEmpty() {
    double stdDev = StatisticsUtil.calculateStandardDeviation(List.of(), 20.0);

    assertThat(stdDev).isEqualTo(0.0);
  }

  @Test
  void shouldRoundCorrectly() {
    double result = StatisticsUtil.round(12.34567);

    assertThat(result).isEqualTo(12.35);
  }
}

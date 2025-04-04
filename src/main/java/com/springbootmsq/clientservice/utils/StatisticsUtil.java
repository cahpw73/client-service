package com.springbootmsq.clientservice.utils;

import java.util.List;

public class StatisticsUtil {

  public static final double DEFAULT_AVERAGE_AGE = 0.0;
  public static final double DEFAULT_STANDARD_DEVIATION = 0.0;

  public static double calculateAverage(List<Integer> ages) {
    return ages.stream()
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0);
  }

  public static double calculateStandardDeviation(List<Integer> ages, double average) {
    return Math.sqrt(ages.stream()
        .mapToDouble(age -> Math.pow(age - average, 2))
        .average()
        .orElse(0));
  }

  public static double round(double value) {
    return Math.round(value * 100.0) / 100.0;
  }

}

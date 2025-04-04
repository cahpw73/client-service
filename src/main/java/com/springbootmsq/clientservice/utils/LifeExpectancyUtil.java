package com.springbootmsq.clientservice.utils;

import java.util.concurrent.ThreadLocalRandom;

public class LifeExpectancyUtil {
  private static final int MIN_EXPECTANCY_YEARS = 60;
  private static final int MAX_EXPECTANCY_YEARS = 80;

  public static int generateLifeExpectancy() {
    return ThreadLocalRandom.current().nextInt(MIN_EXPECTANCY_YEARS, MAX_EXPECTANCY_YEARS + 1);
  }
}

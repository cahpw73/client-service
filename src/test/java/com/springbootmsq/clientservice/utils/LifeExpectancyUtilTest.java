package com.springbootmsq.clientservice.utils;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LifeExpectancyUtilTest {

	@Test
	void shouldReturnLifeExpectancyWithinRange() {
		int expectancy = LifeExpectancyUtil.generateLifeExpectancy();
		assertThat(expectancy)
				.isGreaterThanOrEqualTo(60)
				.isLessThanOrEqualTo(80);
	}

	@RepeatedTest(100)
	void shouldAlwaysReturnValueWithinExpectedRange() {
		int expectancy = LifeExpectancyUtil.generateLifeExpectancy();
		assertThat(expectancy).isBetween(60, 80);
	}
}

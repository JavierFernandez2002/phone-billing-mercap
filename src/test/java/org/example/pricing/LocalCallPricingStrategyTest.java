package org.example.pricing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalCallPricingStrategyTest {
  private final LocalCallPricingStrategy pricingStrategy = new LocalCallPricingStrategy();

  @Test
  void shouldReturnBusinessHoursRateOnWeekdayBetween8And20(){
    LocalDateTime dateTime = LocalDateTime.of(2026,5,6,10,0);//Monday

    BigDecimal rate = pricingStrategy.getRateFor(dateTime);

    assertEquals(BigDecimal.valueOf(0.20), rate);
  }

  @Test
  void shouldReturnNonBusinessHoursRateOnWeekdayOutsideBusinessHours(){
    LocalDateTime dateTime = LocalDateTime.of(2026, 4,6,22,0);

    BigDecimal rate = pricingStrategy.getRateFor(dateTime);

    assertEquals(BigDecimal.valueOf(0.10), rate);
  }
}

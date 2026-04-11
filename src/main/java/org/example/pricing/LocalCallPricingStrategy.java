package org.example.pricing;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class LocalCallPricingStrategy {
  private static final BigDecimal BUSINESS_HOURS_RATE = BigDecimal.valueOf(0.20);
  private static final BigDecimal NON_BUSINESS_HOURS_RATE = BigDecimal.valueOf(0.10);

  private static final Integer BUSINESS_START_HOUR = 8;
  private static final int BUSINESS_END_HOUR = 20;

  public BigDecimal getRateFor(LocalDateTime startDateTime){
    DayOfWeek dayOfWeek = startDateTime.getDayOfWeek();
    Integer hour = startDateTime.getHour();

    if (isWeekend(dayOfWeek)) {
      return NON_BUSINESS_HOURS_RATE;
    }

    if (isBusinessHours(hour)) {
      return BUSINESS_HOURS_RATE;
    }

    return NON_BUSINESS_HOURS_RATE;
  }

  private boolean isWeekend(DayOfWeek dayOfWeek) {
    return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
  }

  private boolean isBusinessHours(Integer hour) {
    return hour >= BUSINESS_START_HOUR && hour < BUSINESS_END_HOUR;
  }
}

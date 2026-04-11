package org.example.models;

import org.example.pricing.LocalCallPricingStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LocalCall extends Call{
  private final LocalCallPricingStrategy pricingStrategy;

  public LocalCall(LocalDateTime startDateTime, Integer durationInMinutes, LocalCallPricingStrategy pricingStrategy) {
    super(startDateTime, durationInMinutes);
    if (pricingStrategy == null) {
      throw new IllegalArgumentException("Pricing strategy cannot be null");
    }
    this.pricingStrategy = pricingStrategy;
  }

  @Override
  public CallType getType() {
    return CallType.LOCAL;
  }

  @Override
  public BigDecimal calculateCost() {
    BigDecimal ratePerMinute = pricingStrategy.getRateFor(getStartDateTime());
    return ratePerMinute.multiply(BigDecimal.valueOf(getDurationInMinutes()));
  }

  @Override
  public String getDescription() {
    return "Local call";
  }
}

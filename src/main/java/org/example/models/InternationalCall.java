package org.example.models;

import org.example.pricing.InternationalPricingRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InternationalCall extends Call{
  private final String destinationCountry;
  private final InternationalPricingRepository pricingRepository;

  public InternationalCall(LocalDateTime startDateTime, Integer durationInMinutes, String destinationCountry, InternationalPricingRepository pricingRepository) {
    super(startDateTime, durationInMinutes);
    if (destinationCountry == null || destinationCountry.isBlank()) {
      throw new IllegalArgumentException("Destination country cannot be null or blank");
    }
    if (pricingRepository == null) {
      throw new IllegalArgumentException("Pricing repository cannot be null");
    }
    this.destinationCountry = destinationCountry;
    this.pricingRepository = pricingRepository;
  }

  public String getDestinationCountry() {
    return destinationCountry;
  }

  @Override
  public CallType getType() {
    return CallType.INTERNATIONAL;
  }

  @Override
  public BigDecimal calculateCost() {
    BigDecimal ratePerMinute = pricingRepository.getRateFor(destinationCountry);
    return ratePerMinute.multiply(BigDecimal.valueOf(getDurationInMinutes()));
  }

  @Override
  public String getDescription() {
    return "International call to " + destinationCountry;
  }
}

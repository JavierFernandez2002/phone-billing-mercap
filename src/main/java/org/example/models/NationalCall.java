package org.example.models;

import org.example.pricing.NationalPricingRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NationalCall extends Call{
  private final String destinationLocality;
  private final NationalPricingRepository pricingRepository;

  public NationalCall(LocalDateTime startDateTime, Integer durationInMinutes, String destinationLocality, NationalPricingRepository pricingRepository) {
    super(startDateTime, durationInMinutes);

    if (destinationLocality == null || destinationLocality.isBlank()) {
      throw new IllegalArgumentException("Destination locality cannot be null or blank");
    }
    if (pricingRepository == null) {
      throw new IllegalArgumentException("Pricing repository cannot be null");
    }

    this.destinationLocality = destinationLocality;
    this.pricingRepository = pricingRepository;
  }


  @Override
  public CallType getType() {
    return CallType.NATIONAL;
  }

  @Override
  public BigDecimal calculateCost() {
    BigDecimal ratePerMinute = pricingRepository.getRateFor(destinationLocality);
    return ratePerMinute.multiply(BigDecimal.valueOf(getDurationInMinutes()));
  }

  @Override
  public String getDescription() {
    return "National call to " + destinationLocality;
  }
}

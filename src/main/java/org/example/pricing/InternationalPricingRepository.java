package org.example.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InternationalPricingRepository {
  private final Map<String, BigDecimal> ratesByCountry;

  public InternationalPricingRepository(){
    this.ratesByCountry = new HashMap<>();
  }

  public void addRate(String country, BigDecimal ratePerMinute) {
    validateDestination(country);
    validateRate(ratePerMinute);

    ratesByCountry.put(country, ratePerMinute);
  }

  public BigDecimal getRateFor(String country) {
    validateDestination(country);

    BigDecimal rate = ratesByCountry.get(country);
    if (rate == null) {
      throw new IllegalArgumentException("No international rate configured for country: " + country);
    }

    return rate;
  }

  private void validateDestination(String country) {
    if (country == null || country.isBlank()) {
      throw new IllegalArgumentException("Country cannot be null or blank");
    }
  }

  private void validateRate(BigDecimal ratePerMinute) {
    if (ratePerMinute == null || ratePerMinute.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Rate per minute cannot be null or negative");
    }
  }
}

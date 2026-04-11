package org.example.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class NationalPricingRepository {
  private final Map<String, BigDecimal> ratesByLocality;

  public NationalPricingRepository() {
    this.ratesByLocality = new HashMap<>();
  }

  public void addRate(String locality, BigDecimal ratePerMinute){
    validateDestination(locality);
    validateRate(ratePerMinute);

    ratesByLocality.put(locality, ratePerMinute);
  }

  public BigDecimal getRateFor(String locality){
    validateDestination(locality);

    BigDecimal rate = ratesByLocality.get(locality);
    if (rate == null ){
      throw new IllegalArgumentException("No national rate configured for locality: " + locality);
    }
    return rate;
  }

  private void validateDestination(String locality){
    if (locality == null || locality.isBlank()){
      throw new IllegalArgumentException("Locality cannot be null or blank");
    }
  }
  private void validateRate(BigDecimal ratePerminute){
    if(ratePerminute == null || ratePerminute.compareTo(BigDecimal.ZERO)<0){
      throw new IllegalArgumentException("Rate per minute cannot be null or negative");
    }
  }
}

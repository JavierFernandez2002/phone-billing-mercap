package org.example.model;

import org.example.models.NationalCall;
import org.example.pricing.NationalPricingRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NationalCallTest {
  @Test
  void shouldCalculateCostUsingConfiguredLocalityRate(){
    NationalPricingRepository repository = new NationalPricingRepository();
    repository.addRate("Cordoba", BigDecimal.valueOf(0.30));

    NationalCall call = new NationalCall(
        LocalDateTime.of(2026,4,8,18,0),
        30,
        "Cordoba",
        repository
    );

    BigDecimal cost = call.calculateCost();

    assertEquals(BigDecimal.valueOf(9.0), cost);
  }
}

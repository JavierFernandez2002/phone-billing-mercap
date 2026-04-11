package org.example.model;
import org.example.models.InternationalCall;
import org.example.pricing.InternationalPricingRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternationalCallTest {
  @Test
  void shouldCalculateCostUsingConfiguredCountryRate() {
    InternationalPricingRepository repository = new InternationalPricingRepository();
    repository.addRate("USA", BigDecimal.valueOf(1.20));

    InternationalCall call = new InternationalCall(
        LocalDateTime.of(2026, 4,12,21,0),
        15,
        "USA",
        repository
    );

    BigDecimal cost = call.calculateCost();

    assertEquals(BigDecimal.valueOf(18.0), cost);
  }
}
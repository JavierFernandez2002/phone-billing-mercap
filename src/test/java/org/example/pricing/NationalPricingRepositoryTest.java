package org.example.pricing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

public class NationalPricingRepositoryTest {
  @Test
  void shouldReturnConfiguredRate(){
    NationalPricingRepository repository = new NationalPricingRepository();
    repository.addRate("Cordoba", BigDecimal.valueOf(0.30));

    BigDecimal rate = repository.getRateFor("Cordoba");

    assertEquals(BigDecimal.valueOf(0.30), rate);
  }

  @Test
  void shouldThrowExceptionWhenRateIsMissing(){
    NationalPricingRepository repository = new NationalPricingRepository();
    assertThrows(IllegalArgumentException.class, () -> repository.getRateFor("Cordoba"));
  }
}

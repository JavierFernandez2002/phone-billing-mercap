package org.example.service;
import org.example.models.Customer;
import org.example.models.InternationalCall;
import org.example.models.Invoice;
import org.example.models.LocalCall;
import org.example.models.NationalCall;
import org.example.pricing.InternationalPricingRepository;
import org.example.pricing.LocalCallPricingStrategy;
import org.example.pricing.LocalCallPricingStrategyTest;
import org.example.pricing.NationalPricingRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingServiceTest {
  @Test
  void shouldGenerateInvoiceForCallsWithinBillingPeriodOnly(){
    LocalCallPricingStrategy localPricingStrategy = new LocalCallPricingStrategy();

    NationalPricingRepository nationalRepository = new NationalPricingRepository();
    nationalRepository.addRate("Cordoba", BigDecimal.valueOf(0.30));

    InternationalPricingRepository internationalRepository = new InternationalPricingRepository();
    internationalRepository.addRate("USA",BigDecimal.valueOf(1.20));

    Customer customer = new Customer(
        "Javier Fernandez",
        "11-5555-1234",
        BigDecimal.valueOf(1500.00)
    );

    customer.addCall(new LocalCall(
        LocalDateTime.of(2026, 4,3,10,30),
        20,
        localPricingStrategy
    )); // 4.00

    customer.addCall(new NationalCall(
        LocalDateTime.of(2026,4,8,18,0),
        30,
        "Cordoba",
        nationalRepository
    )); // 9.00

    customer.addCall(new InternationalCall(
        LocalDateTime.of(2026,4,12,21,0),
        15,
        "USA",
        internationalRepository
    ));// 18.00

    customer.addCall(new LocalCall(
        LocalDateTime.of(2026,3, 28,9,0),
        50,
        localPricingStrategy
    )); // should be ignored

    BillingService billingService = new BillingService();

    Invoice invoice = billingService.generateInvoice(customer, YearMonth.of(2026, 4));

    assertEquals(BigDecimal.valueOf(1500.00), invoice.getMonthlyBaseFee());
    assertEquals(BigDecimal.valueOf(4.00), invoice.getLocalCallsTotal());
    assertEquals(BigDecimal.valueOf(9.00), invoice.getNationalCallsTotal());
    assertEquals(BigDecimal.valueOf(18.00), invoice.getInternationalCallsTotal());
    assertEquals(BigDecimal.valueOf(1531.00), invoice.getTotalAmount());
  }
}

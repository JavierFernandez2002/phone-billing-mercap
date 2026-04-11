package org.example;

import org.example.models.Customer;
import org.example.models.InternationalCall;
import org.example.models.Invoice;
import org.example.models.LocalCall;
import org.example.models.NationalCall;
import org.example.pricing.InternationalPricingRepository;
import org.example.pricing.LocalCallPricingStrategy;
import org.example.pricing.NationalPricingRepository;
import org.example.service.BillingService;
import org.example.service.InvoicePrinter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class Main {
  public static void main(String[] args) {
    LocalCallPricingStrategy localCallPricingStrategy = new LocalCallPricingStrategy();

    NationalPricingRepository nationalPricingRepository = new NationalPricingRepository();
    nationalPricingRepository.addRate("Cordoba", BigDecimal.valueOf(0.30));
    nationalPricingRepository.addRate("Rosario", BigDecimal.valueOf(0.35));
    nationalPricingRepository.addRate("Mendoza", BigDecimal.valueOf(0.40));

    InternationalPricingRepository internationalPricingRepository = new InternationalPricingRepository();
    internationalPricingRepository.addRate("Brazil", BigDecimal.valueOf(0.80));
    internationalPricingRepository.addRate("USA", BigDecimal.valueOf(1.20));
    internationalPricingRepository.addRate("Spain", BigDecimal.valueOf(1.50));

    Customer customer = new Customer(
        "Javier Fernandez",
        "11-5555-1234",
        BigDecimal.valueOf(1500.00)
    );

    customer.addCall(new LocalCall(
        LocalDateTime.of(2026,4,3,10,30),
        20,
        localCallPricingStrategy
    ));

    customer.addCall(new LocalCall(
        LocalDateTime.of(2026, 4, 5, 22, 15),
        10,
        localCallPricingStrategy
    ));

    customer.addCall(new NationalCall(
        LocalDateTime.of(2026,4,8,18,0),
        30,
        "Cordoba",
        nationalPricingRepository
    ));

    customer.addCall(new InternationalCall(
        LocalDateTime.of(2026,4,12,21,0),
        15,
        "USA",
        internationalPricingRepository
    ));

    customer.addCall(new LocalCall(
        LocalDateTime.of(2026, 3, 28, 9, 0),
        50,
        localCallPricingStrategy
    ));

    BillingService billingService = new BillingService();
    Invoice invoice = billingService.generateInvoice(customer, YearMonth.of(2026,4));

    InvoicePrinter printer = new InvoicePrinter();
    printer.print(invoice);

  }
}
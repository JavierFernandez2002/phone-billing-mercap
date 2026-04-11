package org.example.models;

import java.math.BigDecimal;
import java.time.YearMonth;

public class Invoice {
  private final String customerName;
  private final String phoneNumber;
  private final YearMonth billingPeriod;
  private final BigDecimal monthlyBaseFee;
  private final BigDecimal localCallsTotal;
  private final BigDecimal nationalCallsTotal;
  private final BigDecimal internationalCallsTotal;
  private final BigDecimal totalAmount;

  public Invoice(
      String customerName,
      String phoneNumber,
      YearMonth billingPeriod,
      BigDecimal monthlyBaseFee,
      BigDecimal localCallsTotal,
      BigDecimal nationalCallsTotal,
      BigDecimal internationalCallsTotal) {

    if (customerName == null || customerName.isBlank()) {
      throw new IllegalArgumentException("Customer name cannot be null or blank");
    }
    if (phoneNumber == null || phoneNumber.isBlank()) {
      throw new IllegalArgumentException("Phone number cannot be null or blank");
    }
    if (billingPeriod == null) {
      throw new IllegalArgumentException("Billing period cannot be null");
    }
    if (monthlyBaseFee == null || localCallsTotal == null || nationalCallsTotal == null || internationalCallsTotal == null) {
      throw new IllegalArgumentException("Amounts cannot be null");
    }

    this.customerName = customerName;
    this.phoneNumber = phoneNumber;
    this.billingPeriod = billingPeriod;
    this.monthlyBaseFee = monthlyBaseFee;
    this.localCallsTotal = localCallsTotal;
    this.nationalCallsTotal = nationalCallsTotal;
    this.internationalCallsTotal = internationalCallsTotal;
    this.totalAmount = monthlyBaseFee
        .add(localCallsTotal)
        .add(nationalCallsTotal)
        .add(internationalCallsTotal);;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public YearMonth getBillingPeriod() {
    return billingPeriod;
  }

  public BigDecimal getMonthlyBaseFee() {
    return monthlyBaseFee;
  }

  public BigDecimal getLocalCallsTotal() {
    return localCallsTotal;
  }

  public BigDecimal getNationalCallsTotal() {
    return nationalCallsTotal;
  }

  public BigDecimal getInternationalCallsTotal() {
    return internationalCallsTotal;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }
}

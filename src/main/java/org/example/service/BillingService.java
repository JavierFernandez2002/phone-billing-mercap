package org.example.service;

import org.example.models.Call;
import org.example.models.CallType;
import org.example.models.Customer;
import org.example.models.Invoice;

import java.math.BigDecimal;
import java.time.YearMonth;

public class BillingService {
  public Invoice generateInvoice(Customer customer, YearMonth billingPeriod){
    if (customer == null) {
      throw new IllegalArgumentException("Customer cannot be null");
    }
    if (billingPeriod == null) {
      throw new IllegalArgumentException("Billing period cannot be null");
    }

    BigDecimal localCallsTotal = BigDecimal.ZERO;
    BigDecimal nationalCallsTotal = BigDecimal.ZERO;
    BigDecimal internationalCallsTotal = BigDecimal.ZERO;

    for (Call call : customer.getCalls()){
      if (!belongsToBillingPeriod(call, billingPeriod)){
        continue;
      }

      BigDecimal callCost = call.calculateCost();

      if(call.getType() == CallType.LOCAL){
        localCallsTotal = localCallsTotal.add(callCost);
      } else if (call.getType() == CallType.NATIONAL) {
        nationalCallsTotal = nationalCallsTotal.add(callCost);
      } else if (call.getType() == CallType.INTERNATIONAL) {
        internationalCallsTotal = internationalCallsTotal.add(callCost);
      }
    }
    return new Invoice(
        customer.getName(),
        customer.getPhoneNumber(),
        billingPeriod,
        customer.getMonthlyBaseFee(),
        localCallsTotal,
        nationalCallsTotal,
        internationalCallsTotal
    );
  }

  private boolean belongsToBillingPeriod(Call call, YearMonth billingPeriod) {
    return YearMonth.from(call.getStartDateTime()).equals(billingPeriod);
  }
}

package org.example.service;

import org.example.models.Invoice;

public class InvoicePrinter {

  public void print(Invoice invoice){
    if (invoice == null){
      throw new IllegalArgumentException("Invoice cannot be null");
    }

    System.out.println("Invoice for: " + invoice.getCustomerName());
    System.out.println("Phone number: " + invoice.getPhoneNumber());
    System.out.println("Billing period: "+ invoice.getBillingPeriod());

    System.out.println();

    System.out.println("Monthly base fee: "+ invoice.getMonthlyBaseFee());
    System.out.println("Local calls: "+ invoice.getLocalCallsTotal());
    System.out.println("National calls: "+invoice.getNationalCallsTotal());
    System.out.println("International calls: "+ invoice.getInternationalCallsTotal());
    System.out.println();

    System.out.println("Total: "+invoice.getTotalAmount());
  }
}

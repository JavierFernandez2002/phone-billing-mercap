package org.example.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {
  private final String name;
  private final String phoneNumber;
  private final BigDecimal monthlyBaseFee;
  private final List<Call> calls;

  public Customer(String name, String phoneNumber, BigDecimal monthlyBaseFee){
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    if (phoneNumber == null || phoneNumber.isBlank()) {
      throw new IllegalArgumentException("Phone number cannot be null or blank");
    }
    if (monthlyBaseFee == null || monthlyBaseFee.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Monthly base fee cannot be null or negative");
    }

    this.name = name;
    this.phoneNumber = phoneNumber;
    this.monthlyBaseFee = monthlyBaseFee;
    this.calls = new ArrayList<>();
  }

  public void addCall(Call call){
    if (call == null){
      throw new IllegalArgumentException("Call cannot be null");
    }
    calls.add(call);
  }

  public String getName(){
    return name;
  }

  public String getPhoneNumber(){
    return phoneNumber;
  }

  public BigDecimal getMonthlyBaseFee() {
    return monthlyBaseFee;
  }

  public List<Call> getCalls() {
    return List.copyOf(calls);
  }
}

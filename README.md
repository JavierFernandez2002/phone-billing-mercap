# Phone Call Billing System

A small object-oriented solution for a monthly phone call billing exercise.

## Features

- Monthly billing
- Fixed monthly base fee
- Local calls priced by day and time
- National calls priced by destination locality
- International calls priced by destination country
- Console invoice output
- In-memory data only

## Structure

```text
model/
├── Call.java
├── CallType.java
├── LocalCall.java
├── NationalCall.java
├── InternationalCall.java
├── Customer.java
└── Invoice.java

pricing/
├── LocalCallPricingStrategy.java
├── NationalPricingRepository.java
└── InternationalPricingRepository.java

service/
├── BillingService.java
└── InvoicePrinter.java

Main.java
```

## Design

- `Call` is the base abstraction for all calls.
- `LocalCall`, `NationalCall`, and `InternationalCall` model the different billing rules.
- `BillingService` generates the monthly invoice.
- Pricing logic is separated into the `pricing` package.
- `InvoicePrinter` handles console output.

## Assumptions

- Duration is expressed in whole minutes.
- National and international rates are charged per minute.
- Local pricing:
    - Weekdays from 08:00 inclusive to 20:00 exclusive: `0.20`
    - Weekdays outside that range: `0.10`
    - Saturdays and Sundays: `0.10`
- The base fee is fixed per customer.
- No taxes, discounts, or persistence were included.

## How to run

1. Compile the project
2. Run `Main`
3. The program prints an example invoice

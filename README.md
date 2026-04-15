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

## Requirements

- Java 17
- Maven 3.8+ (only needed to run the JUnit test suite)

## Run with Maven

Compile and run tests:

```bash
mvn clean test
```

Run the main program:
``` bash
mvn compile exec:java "-Dexec.mainClass=org.example.Main"
```

## Run with plain Java 17 only

Compile:
``` PowerShell
mkdir out
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
```
Run:
``` PowerShell
java -cp out org.example.Main
```


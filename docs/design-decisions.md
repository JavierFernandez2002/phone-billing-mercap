# Design Decisions

## Goal

Keep the solution simple, object-oriented, and easy to extend.

## Main decisions

### Abstract `Call`
All calls share:
- date/time
- duration

But each type has different pricing rules, so a common abstract `Call` keeps shared data together while letting each subtype define its own behavior.

### Concrete call types
- `LocalCall`
- `NationalCall`
- `InternationalCall`

This makes the model explicit and avoids a single class with too many conditionals.

### Separate pricing from billing
`BillingService` is responsible for generating invoices.

Pricing rules live in the `pricing` package:
- local pricing depends on day/time
- national pricing depends on locality
- international pricing depends on country

### Separate printing from calculation
`BillingService` creates the invoice.
`InvoicePrinter` prints it.

This avoids mixing business logic with presentation.

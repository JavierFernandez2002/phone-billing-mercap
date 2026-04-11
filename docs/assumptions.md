# Assumptions

1. Billing is generated monthly for one customer at a time.
2. Call durations are whole minutes.
3. National and international rates are defined per minute.
4. Missing configured rates produce an error.
5. Local business hours are interpreted as 08:00 inclusive to 20:00 exclusive on weekdays.
6. Weekend local calls always use the lower rate.
7. The monthly base fee is fixed.
8. No taxes, discounts, promotions, or persistence are included.
9. Data is created in memory for demonstration purposes.

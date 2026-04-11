package org.example.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Call {
  private final LocalDateTime startDateTime;
  private final Integer durationInMinutes;

  protected Call(LocalDateTime startDateTime, Integer durationInMinutes){
    if (startDateTime == null){
      throw new IllegalArgumentException("Start date time cannot be null");
    }

    if(durationInMinutes <= 0 ){
      throw new IllegalArgumentException("Duration must be greater than zero");
    }

    this.startDateTime=startDateTime;
    this.durationInMinutes=durationInMinutes;
  }

  public abstract CallType getType();

  public abstract BigDecimal calculateCost();

  public abstract String getDescription();
}

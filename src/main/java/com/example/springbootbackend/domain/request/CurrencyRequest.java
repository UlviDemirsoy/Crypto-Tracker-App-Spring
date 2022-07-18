package com.example.springbootbackend.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyRequest {

    private long id;

    private String currencyName;

    private String symbol;

    private float currentPrice;

    private java.util.Date createdDate;

    private boolean enabled;

    private long userid;

}

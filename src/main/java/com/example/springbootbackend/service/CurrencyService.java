package com.example.springbootbackend.service;
import java.util.List;

import com.example.springbootbackend.domain.request.CurrencyRequest;
import com.example.springbootbackend.model.Currency;

public interface CurrencyService {

    Currency saveCurrency(CurrencyRequest currency);
    List<Currency> getAllCurrencies();
    Currency getCurrencyById(long id);
    Currency updateCurrency(CurrencyRequest currency, long id);
    void deleteCurrency(long id);

}

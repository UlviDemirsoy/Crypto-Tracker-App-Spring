package com.example.springbootbackend.service.impl;


import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Currency;
import com.example.springbootbackend.repository.CurrencyRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Mock
    private CurrencyRepository currencyRepository;



    @Test
    public void shouldGetAllCurrencies() {

        currencyService.getAllCurrencies();
        verify(currencyRepository, times(1)).findAll();

    }

    @Test
    public void shouldSuccessfullyGetCurrencyById() {

        when(currencyRepository.findById(any(long.class))).thenReturn(Optional.of(new Currency()));
        currencyService.getCurrencyById(1234);
        verify(currencyRepository, times(1)).findById((long)1234);

    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenGettingCurrencyById() {
        when(currencyRepository.findById(any(long.class))).thenReturn(Optional.empty());
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> currencyService.getCurrencyById(1234));
        String exceptionMessage = String.format("%s not found with %s: %s", "Currency", "Id", 1234);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());

    }


    @Test
    public void shouldSuccessfullyDeleteCurrency() {
        Currency currency = new Currency();
        currency.setCurrentPrice(4);
        currency.setCurrencyName("example");
        currency.setSymbol("EXM");
        currency.setCreatedDate(new Date());

        when(currencyRepository.findById(any(long.class))).thenReturn(Optional.of(currency));
        currencyService.deleteCurrency(1234);
        verify(currencyRepository, times(1)).deleteById((long)1234);

    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenDeletingCurrency() {
        when(currencyRepository.findById(any(long.class))).thenReturn(Optional.empty());
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> currencyService.deleteCurrency(1234));
        String exceptionMessage = String.format("%s not found with %s: %s","Currency", "Id", 1234);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());

    }
}
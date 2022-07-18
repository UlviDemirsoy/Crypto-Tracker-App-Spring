package com.example.springbootbackend.service.impl;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.example.springbootbackend.model.Alert;
import com.example.springbootbackend.model.Currency;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.AlertRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AlertServiceImplTest {

    @InjectMocks
    private AlertServiceImpl alertService;

    @Mock
    private AlertRepository alertRepository;

    @Mock
    private CurrencyServiceImpl currencyService;

    @Mock
    private UserServiceImpl userService;




    @Test
    public void shouldSuccessfullySaveAlert() {

        Currency currency = new Currency();
        currency.setCurrentPrice(100);
        Alert alert = new Alert();
        alert.setUserId(4);
        alert.setTargetPrice(50);

        when(userService.getUserById(Mockito.any(long.class))).thenReturn(new User());
        when(currencyService.getCurrencyById(any(long.class))).thenReturn(currency);

        alertService.saveAlert(alert);

        verify(alertRepository, times(1)).save(alert);


    }

    @Test
    public void shouldThrowRuntimeExceptionWhenSavingAlert() throws RuntimeException {

        String exceptionMessage = "Targetprice can not be smaller than currentprice";

        Currency currency = new Currency();
        currency.setCurrentPrice(100);
        Alert alert = new Alert();
        alert.setUserId(4);
        alert.setTargetPrice(150);

        when(userService.getUserById(Mockito.any(long.class))).thenReturn(new User());
        when(currencyService.getCurrencyById(any(long.class))).thenReturn(currency);

        Throwable exception = assertThrows(RuntimeException.class, () -> alertService.saveAlert(alert));

        Assertions.assertEquals(exceptionMessage, exception.getMessage());


    }

    @Test
    public void shouldGetAllAlerts() {

        alertService.getAllAlerts();
        verify(alertRepository, times(1)).findAll();

    }

    @Test
    public void shouldSuccessfullyGetAlertById() {

        when(alertRepository.findById(any(long.class))).thenReturn(Optional.of(new Alert()));
        alertService.getAlertById(1234);
        verify(alertRepository, times(1)).findById((long)1234);

    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenGettingAlertById()  throws RuntimeException  {

        when(alertRepository.findById(any(long.class))).thenReturn(Optional.empty());
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> alertService.getAlertById(1234));
        String exceptionMessage = String.format("%s not found with %s: %s","Alert", "Id", 1234);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());

    }

    @Test
    public void shouldSuccessfullyUpdateAlert() {

        Alert alert = new Alert();
        alert.setUserId(4);
        alert.setTargetPrice(50);
        alert.setStatus(Status.NEW);
        alert.setCreatedAt(new Date());

        when(alertRepository.findById(any(long.class))).thenReturn(Optional.of(alert));

        alertService.updateAlert( alert,1234);

        verify(alertRepository, times(1)).save(alert);

    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenUpdatingAlert() {

        Alert alert = new Alert();
        alert.setUserId(4);
        alert.setTargetPrice(50);
        alert.setStatus(Status.NEW);
        alert.setCreatedAt(new Date());

        when(alertRepository.findById(any(long.class))).thenReturn(Optional.empty());
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> alertService.updateAlert(alert,1234));
        String exceptionMessage = String.format("%s not found with %s: %s","Alert", "Id", 1234);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());

    }

    @Test
    public void shouldSuccessfullyDeleteAlert() {

        Alert alert = new Alert();
        alert.setUserId(4);
        alert.setTargetPrice(50);
        alert.setStatus(Status.NEW);
        alert.setCreatedAt(new Date());

        when(alertRepository.findById(any(long.class))).thenReturn(Optional.of(alert));
        alertService.deleteAlert(1234);
        verify(alertRepository, times(1)).deleteById((long)1234);

    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenDeletingAlert() {

        when(alertRepository.findById(any(long.class))).thenReturn(Optional.empty());
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> alertService.deleteAlert(1234));
        String exceptionMessage = String.format("%s not found with %s: %s","Alert", "Id", 1234);
        Assertions.assertEquals(exceptionMessage, exception.getMessage());

    }



}
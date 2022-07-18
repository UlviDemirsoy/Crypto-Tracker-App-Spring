package com.example.springbootbackend.service.impl;
import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Alert;
import com.example.springbootbackend.model.Currency;
import com.example.springbootbackend.model.Status;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.AlertRepository;
import com.example.springbootbackend.service.AlertService;
import com.example.springbootbackend.service.CurrencyService;
import com.example.springbootbackend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.util.List;

@Component
public class AlertServiceImpl implements AlertService {

    private AlertRepository alertRepository;
    private UserService userService;
    private CurrencyService currencyService;

    public AlertServiceImpl(AlertRepository alertRepository, UserService userService, CurrencyService currencyService) {
        super();
        this.alertRepository = alertRepository;
        this.userService = userService;
        this.currencyService = currencyService;
    }

    @Override
    public Alert saveAlert(Alert alert) {

        //check if user and currency exists
        User user = userService.getUserById(alert.getUserId());
        Currency currency = currencyService.getCurrencyById(alert.getUserId());
        if(currency.getCurrentPrice() < alert.getTargetPrice()){
            throw new RuntimeException("Targetprice can not be smaller than currentprice");
        }
        //new alerts
        alert.setStatus(Status.NEW);

        return alertRepository.save(alert);
    }

    @Override
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public Alert getAlertById(long id) {
        return alertRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Alert", "Id", id) );
    }

    @Override
    public Alert updateAlert(Alert alert, long id) {
        //check if record exist
        Alert existingAlert = alertRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Alert", "Id", id));


        existingAlert.setCurrencyId(alert.getCurrencyId());
        existingAlert.setCreatedAt(alert.getCreatedAt());
        existingAlert.setTargetPrice(alert.getTargetPrice());
        existingAlert.setStatus(alert.getStatus());

        alertRepository.save(existingAlert);
        return existingAlert;
    }

    @Override
    public void deleteAlert(long id) {
        //check if it exists
        alertRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Alert", "Id", id));
        alertRepository.deleteById(id);
    }

    @Override
    public Alert updateAlertStatus(Status status, long id) {
        //check if record exist
        Alert existingAlert = alertRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Alert", "Id", id));

        if(status == Status.CANCELLED){
            if(existingAlert.getStatus() == Status.ACKED || (existingAlert.getStatus() == Status.TRIGGERED  )){
                throw new RuntimeException("Triggered Alerts cannot be cancelled!");
            }
        }
        if(status == Status.ACKED){
            if(existingAlert.getStatus() != Status.TRIGGERED){
                throw new RuntimeException("Only Triggered alerts can be acknowledged!");
            }
        }

        existingAlert.setStatus(status);
        alertRepository.save(existingAlert);
        return existingAlert;
    }


}

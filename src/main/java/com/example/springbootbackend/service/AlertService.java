package com.example.springbootbackend.service;
import java.util.List;

import com.example.springbootbackend.model.Alert;
import com.example.springbootbackend.model.Status;


public interface AlertService {
    Alert saveAlert(Alert alert);
    List<Alert> getAllAlerts();
    Alert getAlertById(long id);
    Alert updateAlert(Alert alert, long id);
    void deleteAlert(long id);
    Alert updateAlertStatus(Status status, long id);
}

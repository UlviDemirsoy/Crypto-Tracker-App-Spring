package com.example.springbootbackend.controller;
import java.util.List;
import java.util.Date;
import com.example.springbootbackend.model.Alert;
import com.example.springbootbackend.service.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alert")
public class AlertController {
    private AlertService alertService;

    public AlertController(AlertService alertService) {
        super();
        this.alertService = alertService;
    }


    @PostMapping
    public ResponseEntity<Alert> saveCurrency(@RequestBody Alert alert){
        alert.setCreatedAt(new Date());
        return new ResponseEntity<Alert>(alertService.saveAlert(alert), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Alert> getAllAlerts(){
        return alertService.getAllAlerts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable("id") long id){
        return new ResponseEntity<Alert>(alertService.getAlertById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Alert> updateAlert(@PathVariable("id") long id, @RequestBody Alert alert){
        return new ResponseEntity<Alert>(alertService.updateAlert(alert,id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAlert(@PathVariable("id") long id){
        alertService.deleteAlert(id);
        return new ResponseEntity<String>("Alert deleted successfully!", HttpStatus.OK);
    }


    @PutMapping("updatestatus/{id}")
    public ResponseEntity<Alert> updateAlertStatus(@PathVariable("id") long id, @RequestBody Alert alert){
        return new ResponseEntity<Alert>(alertService.updateAlertStatus(alert.getStatus(),id), HttpStatus.OK);
    }



}

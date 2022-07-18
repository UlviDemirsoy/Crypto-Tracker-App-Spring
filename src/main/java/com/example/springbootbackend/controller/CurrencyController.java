package com.example.springbootbackend.controller;
import java.util.List;
import java.util.Date;

import com.example.springbootbackend.domain.request.CurrencyRequest;
import com.example.springbootbackend.model.Currency;
import com.example.springbootbackend.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        super();
        this.currencyService = currencyService;
    }

    //Create New Currency Rest API
    @PostMapping
    public ResponseEntity<Currency> saveCurrency(@RequestBody CurrencyRequest currency){
        currency.setCreatedDate(new Date());
        return new ResponseEntity<Currency>(currencyService.saveCurrency(currency), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Currency> getAllCurrencies(){
        return currencyService.getAllCurrencies();
    }

    //Get currency by id Rest API
    //http://localhost:8080/api/currency/1

    @GetMapping("{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable("id") long id){
        return new ResponseEntity<Currency>(currencyService.getCurrencyById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable("id") long id, @RequestBody CurrencyRequest currency){
        return new ResponseEntity<Currency>(currencyService.updateCurrency(currency,id), HttpStatus.OK);
    }

    //delete restapi
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCurrency(@PathVariable("id") long id){

        currencyService.deleteCurrency(id);
        return new ResponseEntity<String>("Currency deleted successfully!", HttpStatus.OK);

    }


}

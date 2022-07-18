package com.example.springbootbackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.example.springbootbackend.domain.request.CurrencyRequest;
import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.exception.UnsupportedCurrencyCreationException;
import com.example.springbootbackend.model.Currency;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.CurrencyRepository;
import com.example.springbootbackend.repository.UserRepository;
import com.example.springbootbackend.service.UserService;
import org.springframework.stereotype.Component;
import com.example.springbootbackend.service.CurrencyService;


@Component
public class CurrencyServiceImpl implements CurrencyService{

    private CurrencyRepository currencyRepository;
    private UserService userService;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, UserService userService) {
        super();
        this.currencyRepository = currencyRepository;
        this.userService = userService;
    }





    @Override
    public Currency saveCurrency(CurrencyRequest currency) {

        List<String> blacklist = new ArrayList<String>();
        blacklist.add("ETH");
        blacklist.add("LTC");
        blacklist.add("ZKN");
        blacklist.add("MRD");
        blacklist.add("LPR");
        blacklist.add("GBZ");

        long userid = currency.getUserid();
        //check if user admin
        User user = userService.getUserById(userid);
        if(user.isAdmin() == true){

            Currency currencyEnt = new Currency();
            currencyEnt.setSymbol(currency.getSymbol());
            currencyEnt.setCurrencyName(currency.getCurrencyName());
            currencyEnt.setCurrentPrice(currency.getCurrentPrice());
            currencyEnt.setEnabled(currency.isEnabled());
            currencyEnt.setCreatedDate(currency.getCreatedDate());

            int temp;
            temp = blacklist.contains(currency.getSymbol()) ? 1 : 2;

            if(temp == 1){
                throw new UnsupportedCurrencyCreationException(currency.getSymbol());
            }else{
                return currencyRepository.save(currencyEnt);
            }

        }else{
            throw new RuntimeException("Only Admin Users Can Create Currencies");
        }
    }
    @Override
    public List<Currency> getAllCurrencies() { return currencyRepository.findAll(); }

    @Override
    public Currency getCurrencyById(long id) {
        return currencyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Currency", "Id", id) );
    }

    @Override
    public Currency updateCurrency(CurrencyRequest currency, long id) {
        //check if record exist
        Currency existingCurrency = currencyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Currency", "Id", id));

        long userid = currency.getUserid();
        //check if user admin
        User user = userService.getUserById(userid);
        if(user.isAdmin() == true) {
            existingCurrency.setCurrencyName(currency.getCurrencyName());
            existingCurrency.setCreatedDate(currency.getCreatedDate());
            existingCurrency.setCurrentPrice(currency.getCurrentPrice());
            existingCurrency.setSymbol(currency.getSymbol());
            existingCurrency.setEnabled(currency.isEnabled());
            currencyRepository.save(existingCurrency);
            return existingCurrency;
        }else{
            throw new RuntimeException("Only Admin Users Can Update Currencies");
        }
    }

    @Override
    public void deleteCurrency(long id) {
        //check if it exists
        currencyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Currency", "Id", id));
        currencyRepository.deleteById(id);
    }


}

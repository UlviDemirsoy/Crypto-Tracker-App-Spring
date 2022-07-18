package com.example.springbootbackend.exception;

public class UnsupportedCurrencyCreationException extends RuntimeException {
    private String symbol;

    public UnsupportedCurrencyCreationException(String symbol){
        super(String.format("%s symbol is not supported",symbol));
        this.symbol = symbol;

    }
    public String getSymbol(){
        return symbol;
    }

}

package com.andela.currencycalc.models;

/**
 * Created by JIBOLA on 03-Oct-15.
 */
public class Currency implements Comparable{
    private String currencyName;
    private double currencyRate;

    public Currency(String currencyName, double currencyRate){
        this.currencyName = currencyName;
        this.currencyRate = currencyRate;
    }

    public String getCurrencyName(){
        return currencyName;
    }

    public double getCurrencyRate(){
        return currencyRate;
    }

    @Override
    public int compareTo(Object another) {
        Currency other = (Currency) another;
        if(getCurrencyRate() > other.getCurrencyRate()){
            return 1;
        }
        else if(getCurrencyRate() < other.getCurrencyRate()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
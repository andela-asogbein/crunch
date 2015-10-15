package com.andela.currencycalc.exchangesRates;

/**
 * Created by JIBOLA on 15-Oct-15.
 */
public interface RatesInterface {
    public void fetchRates();
    public double getRateOfCurrency(String currency);
}

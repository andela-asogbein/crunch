package com.andela.currencycalc.exchangesRates;

import com.andela.currencycalc.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class Rates implements RatesInterface{
    private String json;
    private JSONObject jsonObject;
    private JSONObject ratesObject;

    public Rates(){
        fetchRates();
    }

    public void fetchRates(){
        try {
            json = new ExchangeRateDownloader().execute(Constants.exchangeRatesUrl).get();
            jsonObject = new JSONObject(json);
            ratesObject = jsonObject.getJSONObject("rates");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getRateOfCurrency(String currency) {
        double r = 0d;
        try {
            r = ratesObject.getDouble(currency);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }
}

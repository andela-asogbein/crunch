package com.andela.currencycalc.exchangesRates;

import com.andela.currencycalc.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JIBOLA on 07-Oct-15.
 */
public class Rates {

    JSONObject ratesObject;

    public JSONObject fetchRates(){
        try {
            String json = new ExchangeRateDownloader().execute(Constants.exchangeRatesUrl).get();
            JSONObject jsonObject = new JSONObject(json);
            ratesObject = jsonObject.getJSONObject("rates");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ratesObject;
    }
}
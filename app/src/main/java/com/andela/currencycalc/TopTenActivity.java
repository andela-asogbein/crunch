package com.andela.currencycalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andela.currencycalc.constants.Constants;
import com.andela.currencycalc.exchangesRates.ExchangeRateDownloader;
import com.andela.currencycalc.models.Currency;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TopTenActivity extends AppCompatActivity {

    private ListView topTenListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        topTenListView = (ListView)findViewById(R.id.top_ten_list);

        populateCurrencyList();

    }

    public void populateCurrencyList(){
        try {
            String json = new ExchangeRateDownloader().execute(Constants.exchangeRatesUrl).get();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject ratesObject = jsonObject.getJSONObject("rates");
            ArrayList<Currency> currencies = new ArrayList<Currency>();

            Iterator<?> keys = ratesObject.keys();
            while(keys.hasNext()) {
                String key = (String)keys.next();
                currencies.add(new Currency(key, ratesObject.getDouble(key)));
            }
            Collections.sort(currencies);

            ArrayList<String> currencyNames = new ArrayList<String>();
            for(int i = 0; i<11; i++){
                currencyNames.add(currencies.get(i).getCurrencyName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currencyNames);
            topTenListView.setAdapter(adapter);
        }
        catch (Exception e){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_ten, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.calculator:
                Intent i = new Intent(TopTenActivity.this, MainActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
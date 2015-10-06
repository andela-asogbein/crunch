package com.andela.currencycalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.andela.currencycalc.buttons.ButtonHandler;

public class MainActivity extends AppCompatActivity {

    ButtonHandler buttonHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonHandler = new ButtonHandler(this);
    }

    public void numberButtonClickHandler(View v){
        buttonHandler.numberButtonClicked(v.getId());
    }

    public void operatorButtonClickHandler(View v){
        buttonHandler.operatorButtonClicked(v.getId());
    }

    public void equalsButtonClickHandler(View v) {
        buttonHandler.equalsButtonClicked();
    }

    public void decimalButtonClickHandler(View v){
        buttonHandler.decimalButtonClicked(v.getId());
    }

    public void deleteButtonClickHandler(View v){
        buttonHandler.deleteButtonClicked(v.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_ten_currencies:
                Intent i = new Intent(MainActivity.this, TopTenActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
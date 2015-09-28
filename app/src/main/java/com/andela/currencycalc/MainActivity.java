package com.andela.currencycalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ButtonHandler buttonHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonHandler = new ButtonHandler(this);
    }

    public void ButtonClickHandler(View v){
        buttonHandler.buttonClicked(v.getId());
    }
}
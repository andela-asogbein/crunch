package com.andela.currencycalc;

import android.app.Activity;
import android.widget.EditText;

/**
 * Created by JIBOLA on 28-Sep-15.
 */
public class DisplayHandler {

    private EditText display;
    private boolean startNewNumber = true;

    public DisplayHandler(Activity activity){
        display = (EditText)activity.findViewById(R.id.display);
    }

    public void addToDisplay(String userInput){
        if (startNewNumber){
            display.setText("");
            startNewNumber = false;
        }
        display.append(userInput);
    }

    public EditText getDisplay(){
        return display;
    }

    public void setDisplay(String s){
        display.setText(s);
    }

    public void setStartNewNumber(Boolean changeStartNewNumber){
        startNewNumber = changeStartNewNumber;
    }
}
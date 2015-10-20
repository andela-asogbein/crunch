package com.andela.currencycalc.displays;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.andela.currencycalc.R;

public class DisplayHandler {

    private EditText display;
    private TextView secondDisplay;
    private boolean startNewNumber = true;

    public DisplayHandler(Activity activity){
        display = (EditText)activity.findViewById(R.id.display);
        secondDisplay = (TextView)activity.findViewById(R.id.recorder);
    }

    public void addToDisplay(String userInput){
        if (startNewNumber){
            clearScreen();
            startNewNumber = false;
        }
        display.append(userInput);
    }

    public void editDisplay(){
        String currentString = display.getText().toString();
        if(currentString.length() > 0) {
            String editedString = currentString.substring(0, currentString.length() - 1);
            clearScreen();
            display.append(editedString);
        }
    }

    public void addDecimalToDisplay(String userInput){
        if(!display.getText().toString().contains(".")){
            display.append(userInput);
        }
    }

    public void clearScreen(){
        display.setText("");
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

    public void clearSecondDisplay(){
        secondDisplay.setText("");
    }

    public void addToSecondDisplay(String currency, float f, String operator){
        secondDisplay.append(f + currency + " " + operator);
    }

    public void addSingleValueToDisplay(float f, String s) {
        secondDisplay.setText(f+s);
    }
}
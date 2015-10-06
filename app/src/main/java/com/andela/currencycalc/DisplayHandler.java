package com.andela.currencycalc;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

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

    public void addToSecondDisplay(String firstOperandCurrency, float firstOperand, String operator) {
        secondDisplay.setText(firstOperand + firstOperandCurrency + " " + operator);
    }

    public void addToSecondDisplayAgain(float secondOperand, String s) {
        secondDisplay.append(" "+secondOperand+s);
    }
}
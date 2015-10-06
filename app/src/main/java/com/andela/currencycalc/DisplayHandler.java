package com.andela.currencycalc;

import android.app.Activity;
import android.widget.EditText;

public class DisplayHandler {

    private EditText display;
    private boolean startNewNumber = true;

    public DisplayHandler(Activity activity){
        display = (EditText)activity.findViewById(R.id.display);
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
}
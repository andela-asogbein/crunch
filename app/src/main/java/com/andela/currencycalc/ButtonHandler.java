package com.andela.currencycalc;

import android.view.View;

/**
 * Created by JIBOLA on 28-Sep-15.
 */
public class ButtonHandler {

    public void buttonClicked(int viewId) {
        switch(viewId){
            //numbers and decimal
            case R.id.button0 :
                addToDisplay("0");
                break;
            case R.id.button1 :
                addToDisplay("1");
                break;
            case R.id.button2 :
                addToDisplay("2");
                break;
            case R.id.button3 :
                addToDisplay("3");
                break;
            case R.id.button4 :
                addToDisplay("4");
                break;
            case R.id.button5 :
                addToDisplay("5");
                break;
            case R.id.button6 :
                addToDisplay("6");
                break;
            case R.id.button7 :
                addToDisplay("7");
                break;
            case R.id.button8 :
                addToDisplay("8");
                break;
            case R.id.button9 :
                addToDisplay("9");
                break;
            case R.id.buttonPoint :
                addToDisplay(".");
                break;
            //operators
            case R.id.buttonAdd :
                setOperator("+");
                break;
            case R.id.buttonSubtract :
                setOperator("-");
                break;
            case R.id.buttonMultiply :
                setOperator("*");
                break;
            case R.id.buttonDivide :
                setOperator("/");
                break;
            //equals button
            case R.id.buttonEquals :
                calculate();
                startNewNumber = true;
                firstOperand = 0f;
                secondOperand = 0f;
                operator = "";
                break;
        }
    }

}

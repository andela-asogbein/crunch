package com.andela.currencycalc;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by JIBOLA on 28-Sep-15.
 */
public class ButtonHandler {
    DisplayHandler displayHandler;
    MathOperations mathOperations;

    private Spinner operandSpinner;

    private Spinner answerSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapter2;

    public ButtonHandler(Activity activity){
        displayHandler = new DisplayHandler(activity);
        mathOperations = new MathOperations(displayHandler, this);

        initialiseButtonsAndSpinners(activity);
    }

    public void buttonClicked(int viewId) {
        switch(viewId){
            case R.id.button0 :
                displayHandler.addToDisplay("0");
                break;
            case R.id.button1 :
                displayHandler.addToDisplay("1");
                break;
            case R.id.button2 :
                displayHandler.addToDisplay("2");
                break;
            case R.id.button3 :
                displayHandler.addToDisplay("3");
                break;
            case R.id.button4 :
                displayHandler.addToDisplay("4");
                break;
            case R.id.button5 :
                displayHandler.addToDisplay("5");
                break;
            case R.id.button6 :
                displayHandler.addToDisplay("6");
                break;
            case R.id.button7 :
                displayHandler.addToDisplay("7");
                break;
            case R.id.button8 :
                displayHandler.addToDisplay("8");
                break;
            case R.id.button9 :
                displayHandler.addToDisplay("9");
                break;
            case R.id.buttonPoint :
                displayHandler.addToDisplay(".");
                break;
            case R.id.buttonAdd :
                mathOperations.setOperator("+");
                break;
            case R.id.buttonSubtract :
                mathOperations.setOperator("-");
                break;
            case R.id.buttonMultiply :
                mathOperations.setOperator("*");
                break;
            case R.id.buttonDivide :
                mathOperations.setOperator("/");
                break;
            case R.id.buttonEquals :
                mathOperations.calculate();
                displayHandler.setStartNewNumber(true);
                mathOperations.setFirstOperand(0f);
                mathOperations.setSecondOperand(0f);
                mathOperations.setOperator("");
                break;
        }
    }

    public Spinner getOperandSpinner() {
        return operandSpinner;
    }

    public Spinner getAnswerSpinner() {
        return answerSpinner;
    }

    private void initialiseButtonsAndSpinners(Activity activity) {
        operandSpinner = (Spinner)activity.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(activity, R.array.currencies,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operandSpinner.setAdapter(adapter);
        operandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        answerSpinner = (Spinner)activity.findViewById(R.id.targetCurrency);
        adapter2 = ArrayAdapter.createFromResource(activity, R.array.currencies,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        answerSpinner.setAdapter(adapter2);
        answerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
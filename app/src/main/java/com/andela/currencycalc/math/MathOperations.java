package com.andela.currencycalc.math;

import com.andela.currencycalc.exchangesRates.ExchangeRateDownloader;
import com.andela.currencycalc.buttons.ButtonHandler;
import com.andela.currencycalc.constants.Constants;
import com.andela.currencycalc.displays.DisplayHandler;
import com.andela.currencycalc.exchangesRates.Rates;

import org.json.JSONObject;

/**
 * Created by JIBOLA on 28-Sep-15.
 */
public class MathOperations {

    private float firstOperand = 0f;
    private float secondOperand = 0f;
    private String operator = "";

    private String firstOperandCurrency;
    private String secondOperandCurrency;
    private String answerCurrency;

    private DisplayHandler displayHandler;
    private ButtonHandler buttonHandler;

    Rates rates;

    boolean operatorState = false;

    private double answerInTargetCurrency = 0d;
    public MathOperations(DisplayHandler d, ButtonHandler b){
        displayHandler = d;
        buttonHandler = b;
        rates = new Rates();
    }

    public void setOperator(String operatorCharacter){
        if(operatorState == true){
            calculate();
        }
        firstOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());
        firstOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();

        operator = operatorCharacter;

        displayHandler.setStartNewNumber(true);
        displayHandler.addToSecondDisplay(firstOperandCurrency, firstOperand, operator);

        operatorState = true;
    }

    public void calculate() {
        secondOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());
        secondOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();

        answerCurrency = buttonHandler.getAnswerSpinner().getSelectedItem().toString();
        //displayHandler.addToResultCurrencyView(answerCurrency);

        try {
            double targetRate = rates.getRateOfCurrency(answerCurrency);
            double firstOperandInTargetCurrency = 0d;

            if(firstOperandCurrency != null){
                double firstOperandRate = rates.getRateOfCurrency(firstOperandCurrency);
                firstOperandInTargetCurrency = (firstOperand*targetRate)/firstOperandRate;
                displayHandler.addToSecondDisplayAgain(secondOperand, secondOperandCurrency);
            }

            double secondOperandRate = rates.getRateOfCurrency(secondOperandCurrency);
            double secondOperandInTargetCurrency = (secondOperand*targetRate)/secondOperandRate;

            if (operator.equals("+")) {
                answerInTargetCurrency =  firstOperandInTargetCurrency + secondOperandInTargetCurrency;
            }
            else if (operator.equals("-")){
                answerInTargetCurrency =  firstOperandInTargetCurrency - secondOperandInTargetCurrency;
            }
            else if (operator.equals("/")) {
                answerInTargetCurrency =  (firstOperandInTargetCurrency / secondOperandInTargetCurrency);
            }
            else if (operator.equals("x")){
                answerInTargetCurrency =  firstOperandInTargetCurrency * secondOperandInTargetCurrency;
            }
            else {
                answerInTargetCurrency = secondOperandInTargetCurrency;
                displayHandler.addSingleValueToDisplay(secondOperand, secondOperandCurrency);
            }
            displayHandler.setDisplay(String.format("%.2f", answerInTargetCurrency));
        } catch (Exception e) {
            e.printStackTrace();
            displayHandler.setDisplay("No Internet Connection");
        }
        operatorState = false;
    }

    public void setFirstOperand(float f) {
        firstOperand = f;
    }

    public void setSecondOperand(float f) {
        this.secondOperand = f;
    }

    public void resetOperator(){
        operator = "";
    }
}
package com.andela.currencycalc.math;

import com.andela.currencycalc.exchangesRates.ExchangeRates;
import com.andela.currencycalc.buttons.ButtonHandler;
import com.andela.currencycalc.constants.Constants;
import com.andela.currencycalc.displays.DisplayHandler;

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

    private double answerInDollars = 0d;

    public MathOperations(DisplayHandler d, ButtonHandler b){
        displayHandler = d;
        buttonHandler = b;
    }

    public void setOperator(String operatorCharacter){
        firstOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());
        firstOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();

        operator = operatorCharacter;

        displayHandler.setStartNewNumber(true);
        displayHandler.addToSecondDisplay(firstOperandCurrency, firstOperand, operator);
    }

    public void calculate() {
        secondOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());
        secondOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();

        displayHandler.addToSecondDisplayAgain(secondOperand, secondOperandCurrency);

        answerCurrency = buttonHandler.getAnswerSpinner().getSelectedItem().toString();

        try {
            String json = new ExchangeRates().execute(Constants.exchangeRatesUrl).get();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject ratesObject = jsonObject.getJSONObject("rates");

            double firstOperandRate = ratesObject.getDouble(firstOperandCurrency);
            double firstOperandInDollars = firstOperand/firstOperandRate;

            double secondOperandRate = ratesObject.getDouble(secondOperandCurrency);
            double secondOperandInDollars = secondOperand/secondOperandRate;

            double targetRate = ratesObject.getDouble(answerCurrency);

            if (operator.equals("+")) {
                answerInDollars =  firstOperandInDollars + secondOperandInDollars;
                double answerInTargetCurrency = answerInDollars * targetRate;
                displayHandler.setDisplay(String.format("%.2f", answerInTargetCurrency));
            }
            else if (operator.equals("-")){
                answerInDollars =  firstOperandInDollars - secondOperandInDollars;
                double answerInTargetCurrency = answerInDollars * targetRate;
                displayHandler.setDisplay(String.format("%.2f", answerInTargetCurrency));
            }
            else if (operator.equals("/")) {
                answerInDollars =  firstOperandInDollars / secondOperandInDollars;
                double answerInTargetCurrency = answerInDollars;
                displayHandler.setDisplay(String.format("%.2f", answerInTargetCurrency));
            }
            else if (operator.equals("x")){
                float multiply =  firstOperand * secondOperand;
                answerInDollars = multiply/targetRate;
                double answerInTargetCurrency = answerInDollars * targetRate;
                displayHandler.setDisplay(String.format("%.2f", answerInTargetCurrency));
            }
            else if (operator.equals("")){
                answerInDollars = secondOperandInDollars;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFirstOperand(float f) {
        firstOperand = f;
    }

    public void setSecondOperand(float f) {
        this.secondOperand = f;
    }
}
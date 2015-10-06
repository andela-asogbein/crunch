package com.andela.currencycalc;

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
    }

    public void calculate() {
        secondOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());
        secondOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();

        answerCurrency = buttonHandler.getAnswerSpinner().getSelectedItem().toString();

        try {
            String json = new GetExchangeRates().execute(Constants.exchangeRatesUrl).get();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject ratesObject = jsonObject.getJSONObject("rates");

            double firstOperandRate = ratesObject.getDouble(firstOperandCurrency);
            double firstOperandInDollars = firstOperand/firstOperandRate;

            double secondOperandRate = ratesObject.getDouble(secondOperandCurrency);
            double secondOperandInDollars = secondOperand/secondOperandRate;

            double targetRate = ratesObject.getDouble(answerCurrency);

            if (operator.equals("+")) {
                answerInDollars =  firstOperandInDollars + secondOperandInDollars;
            }
            else if (operator.equals("-")){
                answerInDollars =  firstOperandInDollars - secondOperandInDollars;
            }
            else if (operator.equals("*")){
                answerInDollars =  firstOperandInDollars * secondOperandInDollars;
            }
            else if (operator.equals("/")) {
                answerInDollars =  firstOperandInDollars / secondOperandInDollars;
            }
            else if (operator.equals("")){
                answerInDollars = secondOperandInDollars;
            }
            double answerInTargetCurrency = answerInDollars * targetRate;
            displayHandler.setDisplay(String.format("%.2f", answerInTargetCurrency));
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
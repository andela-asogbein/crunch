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

    private static String url = "https://openexchangerates.org/api/latest.json?app_id=f408d4a6aa764e66b2d6f46d2f524e4f";

    public MathOperations(DisplayHandler d, ButtonHandler b){
        displayHandler = d;
        buttonHandler = b;
    }

    public void setOperator(String operatorCharacter){

        firstOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());

        firstOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();

        if (operatorCharacter.equals("+")){
            operator = "+";
        }
        else if (operatorCharacter.equals("-")){
            operator = "-";
        }
        else if (operatorCharacter.equals("*")){
            operator = "*";
        }
        else if (operatorCharacter.equals("/")){
            operator = "/";
        }

        displayHandler.setStartNewNumber(true);
    }

    public void calculate() {
        secondOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());
        secondOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();

        answerCurrency = buttonHandler.getAnswerSpinner().getSelectedItem().toString();

        float answerInDollars = 0f;

        try {
            String json = new GetExchangeRates().execute(url).get();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject ratesObject = jsonObject.getJSONObject("rates");

            Double firstOperandRate = ratesObject.getDouble(firstOperandCurrency);
            Double firstOperandInDollars = firstOperand/firstOperandRate;

            Double secondOperandRate = ratesObject.getDouble(secondOperandCurrency);
            Double secondOperandInDollars = secondOperand/secondOperandRate;

            Double targetRate = ratesObject.getDouble(answerCurrency);

            if (operator.equals("+")) {
                answerInDollars = (float) (firstOperandInDollars + secondOperandInDollars);
            }
            else if (operator.equals("-")){
                answerInDollars = (float) (firstOperandInDollars - secondOperandInDollars);
            }
            else if (operator.equals("*")){
                answerInDollars = (float) (firstOperandInDollars * secondOperandInDollars);
            }
            else if (operator.equals("/")) {
                answerInDollars = (float) (firstOperandInDollars / secondOperandInDollars);
            }
            Double answerInTargetCurrency = answerInDollars * targetRate;
            displayHandler.setDisplay(answerInTargetCurrency + "");
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
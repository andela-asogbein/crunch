//package com.andela.currencycalc.math;
//
//import com.andela.currencycalc.exchangesRates.ExchangeRateDownloader;
//import com.andela.currencycalc.buttons.ButtonHandler;
//import com.andela.currencycalc.constants.Constants;
//import com.andela.currencycalc.displays.DisplayHandler;
//
//import org.json.JSONObject;
//
///**
// * Created by JIBOLA on 28-Sep-15.
// */
//public class MathOperations {
//
//    private float firstOperand = 0f;
//    private float secondOperand = 0f;
//    private String operator = "";
//
//    private String firstOperandCurrency;
//    private String secondOperandCurrency;
//    private String answerCurrency;
//
//    private DisplayHandler displayHandler;
//    private ButtonHandler buttonHandler;
//
//    String json;
//    JSONObject jsonObject;
//    JSONObject ratesObject;
//
//    private double answerInTargetCurrency = 0d;
//    public MathOperations(DisplayHandler d, ButtonHandler b){
//        displayHandler = d;
//        buttonHandler = b;
//        fetchRates();
//    }
//
//    public void fetchRates(){
//        try {
//            json = new ExchangeRateDownloader().execute(Constants.exchangeRatesUrl).get();
//            jsonObject = new JSONObject(json);
//            ratesObject = jsonObject.getJSONObject("rates");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setOperator(String operatorCharacter){
//        firstOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());
//        firstOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();
//
//        operator = operatorCharacter;
//
//        displayHandler.setStartNewNumber(true);
//        displayHandler.addToSecondDisplay(firstOperandCurrency, firstOperand, operator);
//    }
//
//    public void calculate() {
//        secondOperand = Float.parseFloat(displayHandler.getDisplay().getText().toString());
//        secondOperandCurrency = buttonHandler.getOperandSpinner().getSelectedItem().toString();
//
//        displayHandler.addToSecondDisplayAgain(secondOperand, secondOperandCurrency);
//
//        answerCurrency = buttonHandler.getAnswerSpinner().getSelectedItem().toString();
//        displayHandler.addToResultCurrencyView(answerCurrency);
//
//        try {
//            double targetRate =  ratesObject.getDouble(answerCurrency);
//
//            double firstOperandRate =  ratesObject.getDouble(firstOperandCurrency);
//            double firstOperandInTargetCurrency = (firstOperand*targetRate)/firstOperandRate;
//
//            double secondOperandRate =  ratesObject.getDouble(secondOperandCurrency);
//            double secondOperandInTargetCurrency = (secondOperand*targetRate)/secondOperandRate;
//
//            if (operator.equals("+")) {
//                answerInTargetCurrency =  firstOperandInTargetCurrency + secondOperandInTargetCurrency;
//            }
//            else if (operator.equals("-")){
//                answerInTargetCurrency =  firstOperandInTargetCurrency - secondOperandInTargetCurrency;
//            }
//            else if (operator.equals("/")) {
//                answerInTargetCurrency =  (firstOperandInTargetCurrency / secondOperandInTargetCurrency);
//            }
//            else if (operator.equals("x")){
//                answerInTargetCurrency =  firstOperandInTargetCurrency * secondOperandInTargetCurrency;
//            }
//            else {
//                answerInTargetCurrency = secondOperandInTargetCurrency;
//            }
//            displayHandler.setDisplay(String.format("%.2f", answerInTargetCurrency));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setFirstOperand(float f) {
//        firstOperand = f;
//    }
//
//    public void setSecondOperand(float f) {
//        this.secondOperand = f;
//    }
//}
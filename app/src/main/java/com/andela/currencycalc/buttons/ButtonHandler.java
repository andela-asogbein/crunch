package com.andela.currencycalc.buttons;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.andela.currencycalc.displays.DisplayHandler;
import com.andela.currencycalc.math.MathOperations;
import com.andela.currencycalc.R;

public class ButtonHandler {
    private final Activity activity;
    private DisplayHandler displayHandler;
    private MathOperations mathOperations;

    private Spinner operandSpinner;

    private Spinner answerSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapter2;

    private boolean clearSecondDisplay = false;

    public ButtonHandler(Activity activity){
        this.activity = activity;
        displayHandler = new DisplayHandler(activity);
        mathOperations = new MathOperations(displayHandler, this);
        initialiseButtonsAndSpinners(activity);
    }

    public void numberButtonClicked(int viewId) {
        if(clearSecondDisplay){
            displayHandler.clearSecondDisplay();
            clearSecondDisplay = false;
        }
        Button button =  (Button)activity.findViewById(viewId);
        String buttonText = button.getText().toString();
        displayHandler.addToDisplay(buttonText);
    }

    public void operatorButtonClicked(int viewId){
        Button button =  (Button)activity.findViewById(viewId);
        String buttonText = button.getText().toString();
        mathOperations.setOperator(buttonText);
    }

    public void decimalButtonClicked(int viewId){
        Button button =  (Button)activity.findViewById(viewId);
        String buttonText = button.getText().toString();
        displayHandler.addDecimalToDisplay(buttonText);
    }

    public void deleteButtonClicked(int viewId){
        displayHandler.editDisplay();
    }

    public void equalsButtonClicked(){
        mathOperations.equals();
        displayHandler.setStartNewNumber(true);
        mathOperations.setFirstOperand(0f);
        mathOperations.setSecondOperand(0f);
        mathOperations.resetOperator();
        clearSecondDisplay = true;
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
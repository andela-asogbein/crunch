package com.andela.currencycalc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Spinner operandSpinner;
    private Spinner answerSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapter2;

    private EditText display;


    private float firstOperand = 0f;
    private float secondOperand = 0f;
    private String operator = "";
    private float answer = 0f;

    private String firstOperandCurrency;
    private String secondOperandCurrency;
    private String answerCurrency;

    boolean startNewNumber = true;

    private static String url = "https://openexchangerates.org/api/latest.json?app_id=f408d4a6aa764e66b2d6f46d2f524e4f";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (EditText)findViewById(R.id.display);

        operandSpinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.currencies,android.R.layout.simple_spinner_item);
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

        answerSpinner = (Spinner)findViewById(R.id.targetCurrency);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.currencies,android.R.layout.simple_spinner_item);
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

    public void ButtonClickHandler(View v){
        switch(v.getId()){
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

    public void addToDisplay(String userInput){
        if (startNewNumber){
            display.setText("");
            startNewNumber = false;
        }
        display.append(userInput);
    }

    public void setOperator(String operatorCharacter){

        firstOperand = Float.parseFloat(display.getText().toString());
        firstOperandCurrency = operandSpinner.getSelectedItem().toString();

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

        startNewNumber = true;
    }

    public void calculate() {
        secondOperand = Float.parseFloat(display.getText().toString());
        secondOperandCurrency = operandSpinner.getSelectedItem().toString();

        answerCurrency = answerSpinner.getSelectedItem().toString();

        float answerInDollars = 0f;

        try {
            String json = new GetWebPageTask().execute(url).get();
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
            display.setText(answerInTargetCurrency + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class GetWebPageTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... url) {
            return getWebsite(url[0]);
        }
    }

    private String getWebsite(String address) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;

        try{
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line  = reader.readLine()) != null){
                stringBuffer.append(line);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try{
                    reader.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }
}
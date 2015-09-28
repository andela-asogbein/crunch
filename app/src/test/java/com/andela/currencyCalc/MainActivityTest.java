package com.andela.currencyCalc;

import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import com.andela.currencycalc.BuildConfig;
import com.andela.currencycalc.DisplayHandler;
import com.andela.currencycalc.MainActivity;
import com.andela.currencycalc.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.robolectric.shadows.ShadowView.clickOn;
import static org.robolectric.shadows.ShadowView.innerText;

/**
 * Created by JIBOLA on 28-Sep-15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    @Test
    //As a user
    //i need to be able to add, subtract and divide and multiply numbers
    //so i can get answers to my currency arithmetic questions
    public void userSeesNumberOnDisplayWhenShePressesNumberButton(){
        //given i'm a user
        //when i'm on the main screen
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        //and i click a number button e.g button number 2
        clickOn(activity.findViewById(R.id.button2));
        //then i should see the number on that button on the screen e.g see number 2 on the screen
        EditText e = (EditText)activity.findViewById(R.id.display);
        String s = e.getText().toString();
        assertEquals("contains", s, "2");

        //when i click on an operator button
        //the next number i press should start afresh and not continue old number

        //when i click on a number after operand
        //the number should start appearing on the screen

        //when i click the equals button
        //then i should see the result of my addition
    }
}

package com.andela.currencyCalc;

import android.os.Build;
import android.widget.EditText;

import com.andela.currencycalc.BuildConfig;
import com.andela.currencycalc.MainActivity;
import com.andela.currencycalc.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.shadows.ShadowView.clickOn;

/**
 * Created by JIBOLA on 28-Sep-15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    @Test
    public void userSeesNumberOnDisplayWhenShePressesNumberButton() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        clickOn(activity.findViewById(R.id.button2));
        EditText e = (EditText) activity.findViewById(R.id.display);
        String s = e.getText().toString();
        assertEquals("contains", s, "2");
    }

    @Test
    public void usersSeesNewNumberOnDisplayAfterPressingOperator(){
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        clickOn(activity.findViewById(R.id.button2));
        EditText e = (EditText) activity.findViewById(R.id.display);
        String s = e.getText().toString();
        assertEquals("contains", s, "2");
        clickOn(activity.findViewById(R.id.buttonAdd));
        clickOn(activity.findViewById(R.id.button4));

        EditText f = (EditText) activity.findViewById(R.id.display);
        String g = f.getText().toString();
        assertEquals("contains", g, "4");
    }

    @Test
    public void usersSeesAnswerOnDisplayAfterPressingEqualsButton(){
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        clickOn(activity.findViewById(R.id.button2));
        EditText e = (EditText) activity.findViewById(R.id.display);
        String s = e.getText().toString();
        assertEquals("contains", s, "2");
        clickOn(activity.findViewById(R.id.buttonAdd));
        clickOn(activity.findViewById(R.id.button4));
        clickOn(activity.findViewById(R.id.buttonEquals));

        EditText f = (EditText) activity.findViewById(R.id.display);
        String g = f.getText().toString();
        assertEquals("contains", g, "6");
    }

//    public void usersSeesTopTenActivityAfterClickingOptionInMenu(){
//        MainActivity homeActivity = Robolectric.buildActivity(MainActivity.class).create().start().visible().get();
//        ShadowActivity shadowHome = shadowOf(homeActivity);
//        Button btnLaunchSchedule = (Button) homeActivity.findViewById(R.id.top_ten_currencies);
//        clickOn(btnLaunchSchedule);
//
//        assertThat(shadowHome.peekNextStartedActivityForResult().intent.getComponent(), equalsTo(new ComponentName(homeActivity, TopTenActivity.class)));
//    }

}

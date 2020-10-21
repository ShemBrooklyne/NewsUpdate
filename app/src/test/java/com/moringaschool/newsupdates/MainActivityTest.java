package com.moringaschool.newsupdates;


import android.content.Intent;
import android.widget.TextView;

import com.moringaschool.newsupdates.ui.MainActivity;
import com.moringaschool.newsupdates.ui.NewsListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent() {
        TextView editTextPersonName = activity.findViewById(R.id.editTextPersonName);
        assertTrue("Shem Brooklyne".equals(editTextPersonName.getText().toString()));
    }

    @Test
    public void secondActivityStarted(){
        activity.findViewById(R.id.GetStartedbutton).performClick();
        Intent expectedIntent = new Intent(activity, NewsListActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}

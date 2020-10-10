package com.moringaschool.newsupdates;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsUpdateActivity extends AppCompatActivity {
    public static final String TAG = NewsUpdateActivity.class.getSimpleName();

    @BindView(R.id.userTextView) TextView mUserTextView;
    @BindView(R.id.newsTextView) ListView mNewsTextView;

    // ListView displaying some hard coded news highlights
    private String[] headlines = new String[] {
            "India has potential to become AI capital in the world: Tata Sons' Chandrasekaran",
            "Health Insurance: Telemedicine, online consultations to be covered amid COVID-19",
            "Here's a way to check how much income tax is paid by you",
            "Staff members wearing protective suits spray disinfectant (AP)\n" +
                    "Swimming pools: Govt issues SOPs for training of athletes, others",
            "A comprehensive health insurance policy offers wider protection and can help you deal with any exigency\n" +
                    "Switch from covid plan to overall health insurance"
    };

    // Toasts on prompt about missing Api!
    private String[] briefings = new String[] {
            "View more about this once I fix the news generating API after the next modeule.",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API"
    };

    //Override calling get methods in main layout and class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsupdate);
        ButterKnife.bind(this);

        mNewsTextView = (ListView) findViewById(R.id.newsTextView);
        mUserTextView = (TextView) findViewById(R.id.userTextView);

        //ArrayAdapter for displaying the listView in Array form.
        NewsScopesArrayAdapter adapter = new NewsScopesArrayAdapter(this, android.R.layout.simple_list_item_1, headlines, briefings);
    }
}

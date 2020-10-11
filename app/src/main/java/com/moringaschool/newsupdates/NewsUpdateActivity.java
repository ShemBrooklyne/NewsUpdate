package com.moringaschool.newsupdates;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsUpdateActivity extends AppCompatActivity {
    public static final String TAG = NewsUpdateActivity.class.getSimpleName();

//    @BindView(R.id.userTextView) EditText mUserTextView;
    @BindView(R.id.newsTextView) ListView mNewsTextView;
    @BindView(R.id.editTextPersonName) TextView mEditTextPersonName;

    // ListView displaying some hard coded news highlights
    private String[] headlines = new String[] {
            "India has potential to become AI capital in the world: Tata Sons' Chandrasekaran",
            "Health Insurance: Telemedicine, online consultations to be covered amid COVID-19",
            "Here's a way to check how much income tax is paid by you",
            "Staff members wearing protective suits spray disinfectant (AP)",
            "Swimming pools: Govt issues SOPs for training of athletes, others",
            "A comprehensive health insurance policy offers wider protection and can help you deal with any exigency",
            "Switch from covid plan to overall health insurance",
            "How can thematic index mutual funds add value to your portfolio?",
            "American multinational technology company Microsoft",
                    "Microsoft to let employees work remotely for up to half weekly working hours",
            "Startup founders share personal stories to help employees focus on mental health",
            "Apple iPhone 11 to sell at lowest price during Amazon sale: Details here",
            "Stocks to Watch: Banks and financials, TCS, Future Retail, Vedanta",
            "Big Billion Day: Flipkart seeks students for 45-day paid internship programme",
            "120 startups to form association to take on Google",
            "Honda offers discounts of upto ₹2.5 lakh on Civic, Amaze, City, WR-V, Jazz"
    };

    // Toasts on prompt about missing Api!
    private String[] briefings = new String[] {
            "View more about this once I fix the news generating API after the next module.",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
            "Ooops! Missing API",
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
        mEditTextPersonName = (TextView) findViewById(R.id.editTextPersonName);

        //ArrayAdapter for displaying the listView in Array form.
        NewsScopesArrayAdapter adapter = new NewsScopesArrayAdapter(this, android.R.layout.simple_list_item_1, headlines, briefings);

        mNewsTextView.setAdapter(adapter);

        mNewsTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String headlines = ((TextView)view).getText().toString();
                Toast.makeText(NewsUpdateActivity.this, headlines, Toast.LENGTH_LONG).show();
            }
        });

        //User's next page display welcome message, Using Intents.
        Log.v("NewsScopeActivity", "In the onItemClickListener!");
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        mEditTextPersonName.setText("Welcome back " + user);
    }
}

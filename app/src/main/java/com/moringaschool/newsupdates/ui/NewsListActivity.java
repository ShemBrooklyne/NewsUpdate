package com.moringaschool.newsupdates.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.newsupdates.adapters.NewsListAdapter;
import com.moringaschool.newsupdates.models.Article;
import com.moringaschool.newsupdates.network.NewsApi;
import com.moringaschool.newsupdates.network.NewsClient;
//import com.moringaschool.newsupdates.NewsScopesArrayAdapter;
import com.moringaschool.newsupdates.models.NewsUpdatesSearchResponse;
import com.moringaschool.newsupdates.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListActivity extends AppCompatActivity {
    public static final String TAG = NewsListActivity.class.getSimpleName();


//    @BindView(R.id.newsTextView) ListView mNewsTextView;
//    @BindView(R.id.editTextPersonName) TextView mEditTextPersonName;



//    RecyclerView implementation

    @BindView(R.id.recyclerView) RecyclerView mRecyclerview;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private NewsListAdapter mAdapter;

    public List<Article> top_headlines;

    // ListView displaying some hard coded news highlights
    private String[] headlines = new String[] {
//            "India has potential to become AI capital in the world: Tata Sons' Chandrasekaran",
//            "Health Insurance: Telemedicine, online consultations to be covered amid COVID-19",
//            "Here's a way to check how much income tax is paid by you",
//            "Staff members wearing protective suits spray disinfectant (AP)",
//            "Swimming pools: Govt issues SOPs for training of athletes, others",
//            "A comprehensive health insurance policy offers wider protection and can help you deal with any exigency",
//            "Switch from covid plan to overall health insurance",
//            "How can thematic index mutual funds add value to your portfolio?",
//            "American multinational technology company Microsoft",
//                    "Microsoft to let employees work remotely for up to half weekly working hours",
//            "Startup founders share personal stories to help employees focus on mental health",
//            "Apple iPhone 11 to sell at lowest price during Amazon sale: Details here",
//            "Stocks to Watch: Banks and financials, TCS, Future Retail, Vedanta",
//            "Big Billion Day: Flipkart seeks students for 45-day paid internship programme",
//            "120 startups to form association to take on Google",
//            "Honda offers discounts of upto ₹2.5 lakh on Civic, Amaze, City, WR-V, Jazz"
    };

    // Toasts on prompt about missing Api!
//    private String[] briefings = new String[] {
////            "View more about this once I fix the news generating API after the next module.",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API",
////            "Ooops! Missing API"
//    };

    //Override calling get methods in main layout and class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsupdate);
        ButterKnife.bind(this);

//        mNewsTextView = (ListView) findViewById(R.id.newsTextView);
//        mEditTextPersonName = (TextView) findViewById(R.id.editTextPersonName);
//
//        //ArrayAdapter for displaying the listView in Array form.
////        NewsScopesArrayAdapter adapter = new NewsScopesArrayAdapter(this, android.R.layout.simple_list_item_1, headlines);
////
////        mNewsTextView.setAdapter(adapter);
//
//        mNewsTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String headlines = ((TextView)view).getText().toString();
//                Toast.makeText(NewsUpdateActivity.this, headlines, Toast.LENGTH_LONG).show();
//            }
//        });

        //User's next page display welcome message, Using Intents.
        Log.v("NewsScopeActivity", "In the onItemClickListener!");
        final Intent intent = getIntent();
        String user = intent.getStringExtra("user");
//        mEditTextPersonName.setText("Welcome back " + user);

        NewsApi client = NewsClient.getClient();

        Call<NewsUpdatesSearchResponse> call = client.getNews("us", "business", "441e5fea5c6d4f29bee20f551a8cc836");

        call.enqueue(new Callback<NewsUpdatesSearchResponse>() {
            @Override
            public void onResponse(Call<NewsUpdatesSearchResponse> call, Response<NewsUpdatesSearchResponse> response) {
                if (response.isSuccessful()) {
                    hideProgressBar();
                    top_headlines = response.body().getArticles();
                    mAdapter = new NewsListAdapter(NewsListActivity.this, top_headlines);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewsListActivity.this);
                    mRecyclerview.setLayoutManager(layoutManager);
                    mRecyclerview.setHasFixedSize(true);
//                    NewsUpdatesSearchResponse NewsResponse = response.body();
//                    Log.i("Response Body", response.message());
//                    List<Article> ArticleList = response.body().getArticles();
//                    String[] top_headlines = new String[ArticleList.size()];
//
//                    for (int i = 0; i < top_headlines.length; i++) {
//                        top_headlines[i] = ArticleList.get(i).getContent();;
//                    }
//
//                    ArrayAdapter adapter = new NewsScopesArrayAdapter(NewsUpdateActivity.this, android.R.layout.simple_list_item_1, top_headlines);
//                    mNewsTextView.setAdapter(adapter);
                    showTop_headlines();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<NewsUpdatesSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:", t);
                hideProgressBar();
                showFailureMessage();
            }
        });
    }



    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showTop_headlines() {
        mRecyclerview.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}

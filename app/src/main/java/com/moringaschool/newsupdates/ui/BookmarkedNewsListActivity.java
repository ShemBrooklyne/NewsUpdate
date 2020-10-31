package com.moringaschool.newsupdates.ui;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.newsupdates.R;
import com.moringaschool.newsupdates.adapters.articleAdapter;
import com.moringaschool.newsupdates.models.Article;

public class BookmarkedNewsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    articleAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the 
    // Firebase Realtime Database 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        // Create a instance of the database and get 
        // its reference 
        mbase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly 
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a 
        // query in the database to fetch appropriate data 
        FirebaseRecyclerOptions<Article> options = new FirebaseRecyclerOptions.Builder<Article>()
                .setQuery(mbase, Article.class)
                .build();
        // Connecting object of required Adapter class to 
        // the Adapter class itself 
        adapter = new articleAdapter(options);
        // Connecting Adapter class with the Recycler view*/ 
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting 
    // data from database on starting of the activity 
    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting 
    // data from database on stoping of the activity 
    @Override protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}


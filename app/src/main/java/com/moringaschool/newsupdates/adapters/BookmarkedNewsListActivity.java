package com.moringaschool.newsupdates.adapters;

import android.os.Bundle;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.newsupdates.Constants;
import com.moringaschool.newsupdates.R;
import com.moringaschool.newsupdates.models.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarkedNewsListActivity extends AppCompatActivity {
    private DatabaseReference mArticlesReference;
    private FirebaseRecyclerAdapter<Article, FirebaseNewsUpdatesViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsupdate);
        ButterKnife.bind(this);

        mArticlesReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Article> options =
                new FirebaseRecyclerOptions.Builder<Article>()
                .setQuery(mArticlesReference, Article.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Article, FirebaseNewsUpdatesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseNewsUpdatesViewHolder firebaseNewsUpdatesViewHolder, int position, @NonNull Article top_headlines) {
                firebaseNewsUpdatesViewHolder.bindTop_headlines(top_headlines);
            }

            @NonNull
            @Override
            public FirebaseNewsUpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.ativity_newsupdate_list, group, false);
                return new FirebaseNewsUpdatesViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAdapter!= null) {
            mFirebaseAdapter.startListening();
        }
    }
}
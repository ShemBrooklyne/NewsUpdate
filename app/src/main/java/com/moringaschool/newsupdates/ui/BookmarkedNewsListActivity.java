package com.moringaschool.newsupdates.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.newsupdates.Constants;
import com.moringaschool.newsupdates.R;
import com.moringaschool.newsupdates.adapters.FirebaseNewsUpdatesViewHolder;
import com.moringaschool.newsupdates.models.Article;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarkedNewsListActivity extends AppCompatActivity {
    private static final String TAG = "BookmarkedNewsListActivity";
    private DatabaseReference mArticlesReference;
    private FirebaseRecyclerAdapter<Article, FirebaseNewsUpdatesViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsupdate);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mArticlesReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES).child(uid);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Article> options =
                new FirebaseRecyclerOptions.Builder<Article>()
                .setQuery(mArticlesReference, Article.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Article, FirebaseNewsUpdatesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseNewsUpdatesViewHolder holder, int position, @NonNull Article top_headlines) {
                holder.bindArticle(top_headlines);
            }

            @NonNull
            @Override
            public FirebaseNewsUpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bookmarked_news_list, parent, false);
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
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_newsupdate);
//        ButterKnife.bind(this);
//
//        mArticlesReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES);
//        setUpFirebaseAdapter();
//    }
//
//    private void setUpFirebaseAdapter(){
//        FirebaseRecyclerOptions<Article> options =
//                new FirebaseRecyclerOptions.Builder<Article>()
//                .setQuery(mArticlesReference, Article.class)
//                .build();
//
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Article, FirebaseNewsUpdatesViewHolder>(options) {
//            @SuppressLint("LongLogTag")
//            @Override
//            protected void onBindViewHolder(@NonNull FirebaseNewsUpdatesViewHolder firebaseNewsUpdatesViewHolder, int position, @NonNull Article top_headlines) {
//                Log.i(TAG, "onBindViewHolder: ");
//                firebaseNewsUpdatesViewHolder.bindTop_headlines(top_headlines);
//            }
//
//            @NonNull
//            @Override
//            public FirebaseNewsUpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ativity_newsupdate_list, parent, false);
//                return new FirebaseNewsUpdatesViewHolder(view);
//            }
//        };
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//        mFirebaseAdapter.startListening();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mFirebaseAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mFirebaseAdapter!= null) {
//            mFirebaseAdapter.startListening();
//        }
//    }
}
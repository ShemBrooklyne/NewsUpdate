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
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.newsupdates.Constants;
import com.moringaschool.newsupdates.R;
import com.moringaschool.newsupdates.adapters.FirebaseNewsUpdatesViewHolder;
import com.moringaschool.newsupdates.models.Article;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarkedNewsListActivity extends AppCompatActivity {
//    private static final String TAG = "BookmarkedNewsListActivity";
    DatabaseReference reference;
    private FirebaseRecyclerOptions<Article> options;
    private FirebaseRecyclerAdapter<Article, FirebaseNewsUpdatesViewHolder> mFirebaseAdapter;
    private RecyclerView recyclerView;
//    private Object FirebaseUser;
//    @BindView(R.id.recyclerView) RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmarked_news);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        recyclerView = findViewById (R.id.bookmarkedRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES).child(uid);

        options = new FirebaseRecyclerOptions.Builder<Article>()
                .setQuery(reference, Article.class).build();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Article, FirebaseNewsUpdatesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseNewsUpdatesViewHolder holder, int position, @NonNull Article model) {
                holder.TitleNameTextView.setText(model.getTitle());
                holder.authorTextView.setText(model.getAuthor());
                holder.NewsImageView.setImageResource(R.drawable.newsupdatesdemo);
            }

            @NonNull
            @Override
            public FirebaseNewsUpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ativity_newsupdate_list, parent, false);
                return new FirebaseNewsUpdatesViewHolder(view);
            }
        };

        mFirebaseAdapter.startListening();
        recyclerView.setAdapter(mFirebaseAdapter);


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


//        ButterKnife.bind(this);
//
//        mArticlesReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES);
//        setUpFirebaseAdapter();
//
//private void setUpFirebaseAdapter(){
//        FirebaseRecyclerOptions<Article> options =
//        new FirebaseRecyclerOptions.Builder<Article>()
//        .setQuery(mArticlesReference, Article.class)
//        .build();
//
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Article, FirebaseNewsUpdatesViewHolder>(options) {
//@SuppressLint("LongLogTag")
//@Override
//protected void onBindViewHolder(@NonNull FirebaseNewsUpdatesViewHolder firebaseNewsUpdatesViewHolder, int position, @NonNull Article top_headlines) {
//        Log.i(TAG, "onBindViewHolder: ");
//        firebaseNewsUpdatesViewHolder.bindArticle(top_headlines);
//        }
//
//@NonNull
//@Override
//public FirebaseNewsUpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ativity_newsupdate_list, parent, false);
//        return new FirebaseNewsUpdatesViewHolder(view);
//        }
//        };


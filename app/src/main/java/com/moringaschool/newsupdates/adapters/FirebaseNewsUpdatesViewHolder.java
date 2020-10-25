package com.moringaschool.newsupdates.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.newsupdates.Constants;
import com.moringaschool.newsupdates.R;
import com.moringaschool.newsupdates.models.Article;
import com.moringaschool.newsupdates.ui.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseNewsUpdatesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseNewsUpdatesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindTop_headlines(Article top_headlines) {
        TextView mTitleNameTextView = (TextView) mView.findViewById(R.id.TitleNameTextView);
        ImageView mNewsImageView = (ImageView) mView.findViewById(R.id.NewsImageView);
        TextView mAuthorTextView = (TextView) mView.findViewById(R.id.authorTextView);

        mTitleNameTextView.setText(top_headlines.getTitle());
        Picasso.get().load(top_headlines.getUrlToImage()).into(mNewsImageView);
        mAuthorTextView.setText(top_headlines.getAuthor());
    }

    @Override
    public void onClick(View v) {

        final ArrayList<Article> top_headlines = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    top_headlines.add(snapshot.getValue(Article.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("top_headlines", Parcels.wrap(top_headlines));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

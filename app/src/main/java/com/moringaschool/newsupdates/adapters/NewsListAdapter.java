package com.moringaschool.newsupdates.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.newsupdates.R;
import com.moringaschool.newsupdates.models.Article;
import com.moringaschool.newsupdates.ui.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {
    private List<Article> mTop_headlines;
    private Context mContext;

    public NewsListAdapter(Context context, List<Article> top_headlines) {
        mContext = context;
        mTop_headlines = top_headlines;
    }

    @Override
    public NewsListAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ativity_newsupdate_list, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mTop_headlines.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.NewsViewHolder holder, int position) {
        holder.bindTop_headlines(mTop_headlines.get(position));
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.NewsImageView) ImageView mNewsImageView;
        @BindView(R.id.TitleNameTextView) TextView mTitleNameTextView;
//        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.authorTextView) TextView mAuthorTextView;
//        @BindView(R.id.publishedAtTextView) TextView mPublishedAtTextView;

        private Context mContext;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            flipIt(v);
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("top_headlines", Parcels.wrap(mTop_headlines));
            mContext.startActivity(intent);
        }

        private void flipIt(final View viewToFlip) {
            ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationX", 0f, 360f);
            flip.setDuration(200);
            flip.start();
        }

        public void bindTop_headlines(Article top_headlines) {
            mTitleNameTextView.setText(top_headlines.getTitle());
            Picasso.get().load(top_headlines.getUrlToImage()).into(mNewsImageView);
//            mCategoryTextView.setText(top_headlines.getContent());
            mAuthorTextView.setText(top_headlines.getAuthor());
//            mPublishedAtTextView.setText(top_headlines.getPublishedAt());
        }
    }
}

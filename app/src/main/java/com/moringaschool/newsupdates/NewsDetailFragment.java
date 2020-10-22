package com.moringaschool.newsupdates;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.newsupdates.models.Article;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @BindView(R.id.NewsImageView) ImageView mImageLabel;
    @BindView(R.id.TitleNameTextView) TextView mTitleNameTextView;
    @BindView(R.id.categoryTextView) TextView mCategoryTextView;
    @BindView(R.id.authorTextView) TextView mAuthorTextView;
    @BindView(R.id.publishedAtTextView) TextView mPublishedAtTextView;
    @BindView(R.id.BookmarkButton) TextView mBookmarkButton;

    private Article mTop_headlines;

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    public static  NewsDetailFragment newInstance(Article top_headlines) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("top_headlines", Parcels.wrap(top_headlines));
        newsDetailFragment.setArguments(args);
        return newsDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTop_headlines = Parcels.unwrap(getArguments().getParcelable("top_headlines"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mTop_headlines.getUrlToImage()).into(mImageLabel);

        List<String> categories = new ArrayList<>();

        mTitleNameTextView.setText(mTop_headlines.getTitle());
        mAuthorTextView.setText (mTop_headlines.getAuthor());
        mCategoryTextView.setText(mTop_headlines.getContent());
        mPublishedAtTextView.setText(mTop_headlines.getPublishedAt());

        return view;
    }
}

package com.moringaschool.newsupdates;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
public class NewsDetailFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @BindView(R.id.NewsImageView) ImageView mImageLabel;
    @BindView(R.id.TitleNameTextView) TextView mTitleNameTextView;
    @BindView(R.id.categoryTextView) TextView mCategoryTextView;
    @BindView(R.id.authorTextView) TextView mAuthorTextView;
    @BindView(R.id.publishedAtTextView) TextView mPublishedAtTextView;
    @BindView(R.id.BookmarkButton) Button mBookmarkButton;

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

        mBookmarkButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mBookmarkButton) {
            DatabaseReference reference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES);
            reference.push().setValue(mTop_headlines);
            Toast.makeText(getContext(), "Bookmarked!", Toast.LENGTH_SHORT).show();
        }
    }
}

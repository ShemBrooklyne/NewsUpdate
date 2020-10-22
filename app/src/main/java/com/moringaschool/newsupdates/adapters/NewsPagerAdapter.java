package com.moringaschool.newsupdates.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.newsupdates.NewsDetailFragment;
import com.moringaschool.newsupdates.models.Article;

import java.util.List;

public class NewsPagerAdapter extends FragmentPagerAdapter {

    private List<Article> mTop_headlines;

    public NewsPagerAdapter (FragmentManager fm, int behaviour, List<Article> top_headlines) {
        super(fm, behaviour);
        mTop_headlines = top_headlines;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NewsDetailFragment.newInstance(mTop_headlines.get(position));
    }

    @Override
    public int getCount() {
        return mTop_headlines.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTop_headlines.get(position).getDescription();
    }
}

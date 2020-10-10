package com.moringaschool.newsupdates;

import android.content.Context;
import android.widget.ArrayAdapter;

public class NewsScopesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mHeadlines;
    private String[] mBriefings;

    public NewsScopesArrayAdapter(Context mContext, int resource, String[] mHeadlines, String[] mBriefings) {
       super(mContext, resource);
       this.mContext = mContext;
       this.mHeadlines = mHeadlines;
       this.mBriefings = mHeadlines;
    }

    @Override
    public Object getItem(int position) {
        String headlines = mHeadlines[position];
        String briefings = mBriefings[position];
        return String.format("%s \n entails of:", headlines, briefings);
    }

    @Override
    public int getCount() {
        return mHeadlines.length;
    }
}

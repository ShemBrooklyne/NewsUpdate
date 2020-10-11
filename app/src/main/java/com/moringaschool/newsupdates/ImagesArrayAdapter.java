package com.moringaschool.newsupdates;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ImagesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mTrending;


    public ImagesArrayAdapter (Context mContext, int resource, String[] mTrending) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mTrending = mTrending;
    }

    @Override
    public Object getItem(int position) {
//        String trending = mTrending[position];
        return 0;
    }

    @Override
    public int getCount() {
        return mTrending.length;
    }
}

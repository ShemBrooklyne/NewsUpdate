package com.moringaschool.newsupdates;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageViewActivity extends AppCompatActivity {
    public static final String Tag = ImageViewActivity.class.getSimpleName();

    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.imageViewText)
    ListView mListView;

    private String[] trending = new String[]{
            "Follow More of the top trending news on Twitter",
            "Follow More of the top trending news on Twitter",
            "Follow More of the top trending news on Twitter"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        ButterKnife.bind(this);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mListView = (ListView) findViewById(R.id.imageViewText);

        ImagesArrayAdapter adapter = new ImagesArrayAdapter(this, android.R.layout.simple_list_item_1, trending); // must match constructor!

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView) view).getText().toString();
//                Toast.makeText(ImageViewActivity.this, trending, Toast.LENGTH_LONG).show();
            }
        });
    }
}

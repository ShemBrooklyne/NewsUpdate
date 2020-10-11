package com.moringaschool.newsupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    @BindView(R.id.userTextView) EditText mUserTextView;
    @BindView(R.id.GetStartedbutton) Button mGetStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mGetStartedButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mGetStartedButton) {
//            String user = mUserTextView.getText().toString();
            Intent intent = new Intent(MainActivity.this, NewsUpdateActivity.class);
//            intent.putExtra("user", user);
            startActivity(intent);
        }
    }
}
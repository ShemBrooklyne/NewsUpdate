package com.moringaschool.newsupdates.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.newsupdates.Constants;
import com.moringaschool.newsupdates.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    @BindView(R.id.userTextView) EditText mUserTextView;
//    @BindView(R.id.editTextPersonName)
//    EditText mEditTextPersonName;
    private DatabaseReference mGetStartedReference;
    private ValueEventListener mGetStartedReferenceListener;
    @BindView(R.id.GetStartedbutton) Button mGetStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mGetStartedReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_TOP_HEADLINES);

        mGetStartedReferenceListener = mGetStartedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mGetStartedButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mGetStartedButton) {
//            String user = mEditTextPersonName.getText().toString();
            Intent intent = new Intent(MainActivity.this, NewsListActivity.class);
//            intent.putExtra("user", user);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGetStartedReference.removeEventListener(mGetStartedReferenceListener);
    }
}

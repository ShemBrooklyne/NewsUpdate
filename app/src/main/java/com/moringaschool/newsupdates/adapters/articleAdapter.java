package com.moringaschool.newsupdates.adapters;

//package com.example.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.newsupdates.Constants;
import com.moringaschool.newsupdates.R;
import com.moringaschool.newsupdates.models.Article;
import com.squareup.picasso.Picasso;


// database contents in a Recycler View

public class articleAdapter extends FirebaseRecyclerAdapter<Article, articleAdapter.personsViewholder> {

    private static final String TAG = "Bookmarked news";
    DatabaseReference reference;
    Context mContext;
    private Article article;



    public articleAdapter(@NonNull FirebaseRecyclerOptions<Article> options) {
        super(options);
    }

    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item_drag, parent, false);
        return new personsViewholder(view);


    }


    @Override
    public void
    onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull Article model) {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TOP_HEADLINES).child(uid);
        DatabaseReference usersRef = rootRef.child("top_headlines");
        usersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Article users = ds.getValue(Article.class);
                    Log.d("result", "User name: " + users.getTitle() + ", email " + users.getDescription());
                    Picasso.get().load(users.getUrlToImage()).into(holder.NewsImageView);
                    holder.TitleNameTextView.setText(users.getTitle());
                    holder.authorTextView.setText(users.getAuthor());


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
 

    }


    class personsViewholder extends RecyclerView.ViewHolder {
        public TextView TitleNameTextView, authorTextView;
        public ImageView NewsImageView;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            TitleNameTextView = itemView.findViewById(R.id.TitleNameTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            NewsImageView = itemView.findViewById(R.id.NewsImageView);
        }
    }
}


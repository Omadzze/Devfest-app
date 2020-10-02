package com.example.devfest.Activites;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devfest.Adapter.SpeakersAdapter;
import com.example.devfest.Model.SpeakerItem;
import com.example.devfest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SpeakersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<SpeakerItem> list;
    private SpeakersAdapter speakersAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);

        recyclerView = findViewById(R.id.recyclerViewSpeakers);
        ImageView backImage = findViewById(R.id.SpekaerBack);
        ImageView shareImage = findViewById(R.id.shareImageSpeakers);
        progressBar = findViewById(R.id.progressBarSpekaer);
        final ImageView trex = findViewById(R.id.trexImageSpeakers);
        TextView noInternet = findViewById(R.id.no_internet_textSpeakers);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        list = new ArrayList<SpeakerItem>();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("speakers");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        SpeakerItem myList = dataSnapshot.getValue(SpeakerItem.class);
                        list.add(myList);

                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    speakersAdapter = new SpeakersAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(speakersAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), R.string.please_try_again, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            trex.setVisibility(View.VISIBLE);
            noInternet.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBack();
            }
        });

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareButton();
            }
        });
    }

    public void onClickBack() {
        Intent intnet = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intnet);
    }

    public void shareButton() {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = "Here you can download app https://github.com/Omadzze";
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

}

package com.example.devfest.Activites;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.example.devfest.Adapter.SponsorsAdatper;
import com.example.devfest.Model.SponsorsItem;
import com.example.devfest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SponsorsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<SponsorsItem> sponsorsList;
    private SponsorsAdatper sponsorsAdatper;

    SpeakersActivity speakersActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        recyclerView = findViewById(R.id.recylcerViewSponsors);
        ProgressBar progressBar1 = findViewById(R.id.progressBarSponsors);
        ImageView backImage = findViewById(R.id.backSponsors);
        ImageView shareImage = findViewById(R.id.shareSponsors);
        TextView noInternet = findViewById(R.id.no_internet_textSponsors);
        final ImageView trex = findViewById(R.id.trexImageSponsors);
        final ProgressBar progressBar = findViewById(R.id.progressBarSponsors);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        sponsorsList = new ArrayList<SponsorsItem>();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("sponsors");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        SponsorsItem myList = dataSnapshot.getValue(SponsorsItem.class);
                        sponsorsList.add(myList);
                    }

                    progressBar.setVisibility(View.INVISIBLE);
                    sponsorsAdatper = new SponsorsAdatper(getApplicationContext(), sponsorsList);
                    recyclerView.setAdapter(sponsorsAdatper);
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

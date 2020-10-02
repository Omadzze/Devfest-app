package com.example.devfest.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.devfest.Adapter.CustomAdapter;
import com.example.devfest.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnMenuListener {

    private int NightMode;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_category_item);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ImageView shareImage = findViewById(R.id.shareImageMenu);
        ImageView nightMode = findViewById(R.id.nightMode);

        List<Integer> titleText = new ArrayList<>();
        List<Integer> cardColor = new ArrayList<>();
        List<Integer> iconImage = new ArrayList<>();

        //Adding Titles to cardView
        titleText.add(R.string.agenda);
        titleText.add(R.string.speakers);
        titleText.add(R.string.register);
        titleText.add(R.string.sponsors);
        titleText.add(R.string.location);
        titleText.add(R.string.about);

        //Adding Colors to CardView
        cardColor.add(R.color.colorYellow);
        cardColor.add(R.color.colorGreen);
        cardColor.add(R.color.colorBlue);
        cardColor.add(R.color.colorRed);
        cardColor.add(R.color.colorGreen);
        cardColor.add(R.color.colorYellow);

        //Adding icons to CardView
        iconImage.add(R.drawable.ic_agenda);
        iconImage.add(R.drawable.ic_speakers);
        iconImage.add(R.drawable.ic_register);
        iconImage.add(R.drawable.ic_sponsors);
        iconImage.add(R.drawable.ic_location);
        iconImage.add(R.drawable.ic_about_us);

        CustomAdapter customAdapter = new CustomAdapter(this, titleText, iconImage, cardColor, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(customAdapter);

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Here you can download app https://github.com/Omadzze";
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });

        sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        NightMode = sharedPreferences.getInt("NightModeInt", 1);
        AppCompatDelegate.setDefaultNightMode(NightMode);

        nightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        NightMode = AppCompatDelegate.getDefaultNightMode();
        sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("NightModeInt", NightMode);
        editor.apply();
    }

    @Override
    public void onMenuClick(int position) {
        final Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(this, AgendaActivity.class);
                break;
            case 1:
                intent = new Intent(this, SpeakersActivity.class);
                break;
            case 2:
                intent = new Intent(this, RegisterActvity.class);
                break;
            case 3:
                intent = new Intent(this, SponsorsActivity.class);
                break;
            case 4:
                intent = new Intent(this, MapActivity.class);
                break;
            case 5:
                intent = new Intent(this, FaqActivity.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }

        this.startActivity(intent);
    }
}
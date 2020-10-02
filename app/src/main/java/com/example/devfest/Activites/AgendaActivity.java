package com.example.devfest.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.devfest.Adapter.AgendaMenuAdapter;
import com.example.devfest.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AgendaActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        ViewPager2 viewPager2 = findViewById(R.id.viewPagerAgenda);
        viewPager2.setAdapter(new AgendaMenuAdapter(this));
        ImageView backImage = findViewById(R.id.backImageAgenda);
        ImageView shareImage = findViewById(R.id.shareImageMenu);

        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.black));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.blue));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0: {
                        tab.setText(R.string.cloud);
                        tab.setIcon(R.drawable.ic_cloud);
                        tab.getIcon().setAlpha(90);
                        break;
                    }
                    case 1: {
                        tab.setText(R.string.mobile);
                        tab.setIcon(R.drawable.ic_mobile);
                        tab.getIcon().setAlpha(90);
                        break;
                    }
                    case 2: {
                        tab.setText(R.string.web);
                        tab.setIcon(R.drawable.ic_chrome);
                        tab.getIcon().setAlpha(90);
                        break;
                    }
                }
            }
        }
        );
        tabLayoutMediator.attach();

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

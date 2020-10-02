package com.example.devfest.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.devfest.Activites.MainActivity;
import com.example.devfest.Adapter.OnBoardingAdapter;
import com.example.devfest.Model.OnBoardingItem;
import com.example.devfest.R;

import java.util.ArrayList;
import java.util.List;

public class SliderActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnBoardingIndicators;
    private ImageView buttonOnBoardingAction;
    private TextView skipText;
    private TextView getStartedText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        layoutOnBoardingIndicators = findViewById(R.id.linearLayout);
        buttonOnBoardingAction = findViewById(R.id.button_intro);
        skipText = findViewById(R.id.skip_text);
        getStartedText = findViewById(R.id.getStarted_text);

        setupOnBoardingItems();

        final ViewPager2 onBoardingViewPager = findViewById(R.id.viewPager2);
        onBoardingViewPager.setAdapter(onBoardingAdapter);

        setupOnBoardingIndicators();
        setCurrentOnBoardingIndicator(0);

        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });

        buttonOnBoardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBoardingViewPager.getCurrentItem() < onBoardingAdapter.getItemCount()) {
                    onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        getStartedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        //open if first time
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        boolean firstRun = settings.getBoolean("firstRun", false);
        if (firstRun == false) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", true);
            editor.commit();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setupOnBoardingItems() {
        List<OnBoardingItem> onBoardingItems = new ArrayList<>();

        OnBoardingItem firstItem = new OnBoardingItem();
        firstItem.setTitle(R.string.welcome_to_GDG);
        firstItem.setDescription(R.string.gdg_description);
        firstItem.setScreenImage(R.drawable.gdg_logo);
        firstItem.setBgColor(R.color.white);
        firstItem.setColorText(R.color.onBoardText);

        OnBoardingItem secondItem = new OnBoardingItem();
        secondItem.setTitle(R.string.devfest_community);
        secondItem.setDescription(R.string.devfest_description);
        secondItem.setScreenImage(R.drawable.devf);
        secondItem.setBgColor(R.color.blue);
        secondItem.setColorText(R.color.white);

        onBoardingItems.add(firstItem);
        onBoardingItems.add(secondItem);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItems);
    }

    private void setupOnBoardingIndicators() {
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicators.addView(indicators[i]);

        }
    }

    private void setCurrentOnBoardingIndicator(int index) {
        int childCount = layoutOnBoardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnBoardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(), R.drawable.onboarding_indicator_active));
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }

            if (1 == index) {
                skipText.setTextColor(Color.WHITE);
                buttonOnBoardingAction.setVisibility(View.GONE);
                getStartedText.setVisibility(View.VISIBLE);
            } else {
                skipText.setTextColor(Color.BLACK);
                buttonOnBoardingAction.setImageResource(R.drawable.ic_circle_button_black);
                buttonOnBoardingAction.setVisibility(View.VISIBLE);
                getStartedText.setVisibility(View.INVISIBLE);
            }
        }

    }
}

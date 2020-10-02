package com.example.devfest.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devfest.Adapter.FaqAdapter;
import com.example.devfest.Model.FaqItem;
import com.example.devfest.R;

import java.util.ArrayList;
import java.util.List;

public class FaqActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<FaqItem> faqList;
    private ImageView faqBack, faqShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_faq);

        recyclerView = findViewById(R.id.recyclerViewFaq);
        faqBack = findViewById(R.id.faqBack);
        faqShare = findViewById(R.id.faqShare);

        initData();
        initRecyclerView();

        faqBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBack();
            }
        });

        faqShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareButton();
            }
        });
    }

    private void initRecyclerView() {
        FaqAdapter faqAdapter = new FaqAdapter(faqList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(faqAdapter);
    }

    private void initData() {
        faqList = new ArrayList<>();
        faqList.add(new FaqItem(R.string.stay_informed, R.string.stay_informedDescr));
        faqList.add(new FaqItem(R.string.content_formats, R.string.content_formDescr));
        faqList.add(new FaqItem(R.string.livestream, R.string.livestreamDescr));
        faqList.add(new FaqItem(R.string.press_ticket, R.string.press_ticketDescr));
        faqList.add(new FaqItem(R.string.badges, R.string.badgesDescr));
        faqList.add(new FaqItem(R.string.smoking, R.string.smokingDecr));
        faqList.add(new FaqItem(R.string.afterParty, R.string.afterPartyDescr));
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

package com.example.devfest.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.devfest.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class SpeakerInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker_info);

        TextView speakerInfoName = findViewById(R.id.speakerInfoName);
        TextView speakerInfoTitle = findViewById(R.id.speakerInfoTitle);
        TextView speakerInfoDescription = findViewById(R.id.speakerInfoDescription);
        ImageView imageInfoAvatar = findViewById(R.id.imageInfoAvatar);
        ImageView backInfo = findViewById(R.id.backInfo);

        Intent intentImage = getIntent();
        Bundle bundle = intentImage.getExtras();

        if (bundle != null) {
            String image = (String) bundle.get("photoUrl");
            String title = (String) bundle.get("title");
            String description = (String) bundle.get("bio");
            String companyName = (String) bundle.get("companyName");
            String name = (String) bundle.get("name");

            Picasso.get().load(image).fit().centerCrop().into(imageInfoAvatar);

            speakerInfoTitle.setText(title);
            speakerInfoDescription.setText(description);
            speakerInfoName.setText("By " + name + "in " + companyName);
        }

        backInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpeakerInfo.this, SpeakersActivity.class);
                startActivity(intent);
            }
        });
    }
}

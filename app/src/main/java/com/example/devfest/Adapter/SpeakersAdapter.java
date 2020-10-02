package com.example.devfest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devfest.Activites.SpeakerInfo;
import com.example.devfest.Activites.SpeakersActivity;
import com.example.devfest.Model.SpeakerItem;
import com.example.devfest.R;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<SpeakerItem> speakerItems;

    public SpeakersAdapter(Context mcontext, ArrayList<SpeakerItem> speakers) {
        context = mcontext;
        speakerItems = speakers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpeakersAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.speakers_item, parent, false));
    }

    public int randomColor() {
        List<Integer> colors;
        colors = new ArrayList<>();
        colors.add(R.color.colorYellow);
        colors.add(R.color.colorGreen);
        colors.add(R.color.colorBlue);
        colors.add(R.color.colorRed);

        Random rand = new Random();
        return colors.get(rand.nextInt(colors.size()));
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.nameSpeaker.setText(speakerItems.get(position).getName());
        holder.locationSpeaker.setText(speakerItems.get(position).getCountry());
        holder.descriptionSpeaker.setText(speakerItems.get(position).getShortBio());

        Picasso.get().load(speakerItems.get(position).getPhotoUrl()).into(holder.profile_imageSpeakers);

        holder.materialCardView.setStrokeColor(ContextCompat.getColor(context, randomColor()));
        holder.materialCardView.invalidate();

        holder.materialCardView.setTag(position);
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cardIntent = new Intent(view.getContext(), SpeakerInfo.class);
                cardIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cardIntent.putExtra("companyName", speakerItems.get(position).getCompany());
                cardIntent.putExtra("name", speakerItems.get(position).getName());
                cardIntent.putExtra("photoUrl", speakerItems.get(position).getPhotoUrl());
                cardIntent.putExtra("bio", speakerItems.get(position).getBio());
                cardIntent.putExtra("title", speakerItems.get(position).getTitle());
                view.getContext().startActivity(cardIntent);
            }
        });

        if (speakerItems.get(position).getLinkFacebook() == null &&
                speakerItems.get(position).getLinkLinkedIn() == null && speakerItems.get(position).getLinkGithub() == null) {
            holder.speakerFacebook.setVisibility(View.INVISIBLE);
            holder.speakerGithub.setVisibility(View.INVISIBLE);
            holder.speakerLinkdIn.setVisibility(View.INVISIBLE);
        }

        //social Buttons Facebook, Github, LinkedIn
        holder.speakerFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(speakerItems.get(position).getLinkFacebook()));
                view.getContext().startActivity(intent);
            }
        });

        holder.speakerLinkdIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent();
                intentL.setAction(Intent.ACTION_VIEW);
                intentL.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentL.addCategory(Intent.CATEGORY_BROWSABLE);
                intentL.setData(Uri.parse(speakerItems.get(position).getLinkLinkedIn()));
                view.getContext().startActivity(intentL);
            }
        });


        holder.speakerGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentG = new Intent();
                intentG.setAction(Intent.ACTION_VIEW);
                intentG.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentG.addCategory(Intent.CATEGORY_BROWSABLE);
                intentG.setData(Uri.parse(speakerItems.get(position).getLinkGithub()));
                view.getContext().startActivity(intentG);
            }
        });
    }


    @Override
    public int getItemCount() {
        return speakerItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameSpeaker, locationSpeaker, descriptionSpeaker;
        ImageView profile_imageSpeakers, speakerLinkdIn, speakerGithub, speakerFacebook;
        MaterialCardView materialCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSpeaker = itemView.findViewById(R.id.nameSpeaker);
            locationSpeaker = itemView.findViewById(R.id.locationSpeaker);
            descriptionSpeaker = itemView.findViewById(R.id.descriptionSpeaker);
            profile_imageSpeakers = itemView.findViewById(R.id.profile_imageSpeakers);
            materialCardView = itemView.findViewById(R.id.materialCardView);
            speakerFacebook = itemView.findViewById(R.id.speakerFacebook);
            speakerLinkdIn = itemView.findViewById(R.id.speakerLinkedIn);
            speakerGithub = itemView.findViewById(R.id.speakerGithub);

        }
    }
}

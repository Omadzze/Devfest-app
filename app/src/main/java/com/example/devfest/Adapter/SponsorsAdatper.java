package com.example.devfest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devfest.Model.SponsorsItem;
import com.example.devfest.R;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SponsorsAdatper extends RecyclerView.Adapter<SponsorsAdatper.SponsorViewHolder> {

    private Context context;
    private ArrayList<SponsorsItem> sponsorsItems;

    public SponsorsAdatper(Context mcontext, ArrayList<SponsorsItem> sponsors) {
        context = mcontext;
        sponsorsItems = sponsors;
    }

    @NonNull
    @Override
    public SponsorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SponsorsAdatper.SponsorViewHolder(LayoutInflater.from(context).inflate(R.layout.sponsors_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorViewHolder holder, final int position) {

        Picasso.get().load(sponsorsItems.get(position).getImageLink()).into(holder.sponsorImage);

        holder.sponsorMaterialCard.setTag(position);
        holder.sponsorMaterialCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cardIntent = new Intent();
                cardIntent.setAction(Intent.ACTION_VIEW);
                cardIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cardIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                cardIntent.setData(Uri.parse(sponsorsItems.get(position).getWebsite()));
                view.getContext().startActivity(cardIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sponsorsItems.size();
    }

    class SponsorViewHolder extends RecyclerView.ViewHolder {

        ImageView sponsorImage;
        MaterialCardView sponsorMaterialCard;

        public SponsorViewHolder(@NonNull View itemView) {
            super(itemView);
            sponsorImage = itemView.findViewById(R.id.sponsorImage);
            sponsorMaterialCard = itemView.findViewById(R.id.sponsorMaterialCard);
        }
    }
}

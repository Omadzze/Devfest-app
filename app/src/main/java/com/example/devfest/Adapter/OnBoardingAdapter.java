package com.example.devfest.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devfest.Model.OnBoardingItem;
import com.example.devfest.R;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.onBoardingViewHolder> {

    private List<OnBoardingItem> onBoardingItems;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public onBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new onBoardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.acitvity_slider, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull onBoardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    class onBoardingViewHolder extends RecyclerView.ViewHolder {

        ImageView logoImage;
        TextView sliderText;
        TextView sliderDescription;
        ConstraintLayout constrainLayout;

        public onBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            logoImage = itemView.findViewById(R.id.logoImage);
            sliderText = itemView.findViewById(R.id.sliderText);
            sliderDescription = itemView.findViewById(R.id.sliderDescription);
            constrainLayout = itemView.findViewById(R.id.constrainLayout_slider);
        }

        void setOnBoardingData(OnBoardingItem onBoardingItem) {

            sliderText.setText(onBoardingItem.getTitle());
            int colorText = sliderText.getContext().getResources().getColor(onBoardingItem.getColorText());
            sliderText.setTextColor(colorText);

            sliderDescription.setText(onBoardingItem.getDescription());
            sliderDescription.setTextColor(colorText);

            logoImage.setImageResource(onBoardingItem.getScreenImage());
            int colorBackground = constrainLayout.getContext().getResources().getColor(onBoardingItem.getBgColor());
            constrainLayout.setBackgroundColor(colorBackground);
        }
    }
}

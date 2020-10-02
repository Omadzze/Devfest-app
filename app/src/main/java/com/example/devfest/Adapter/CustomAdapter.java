package com.example.devfest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devfest.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Integer> titleText;
    private List<Integer> iconImage;
    private List<Integer> cardColor;

    LayoutInflater inflater;
    private OnMenuListener mOnMenuListener;

    public CustomAdapter(Context ctx, List<Integer> titleText, List<Integer> iconImage, List<Integer> cardColor, OnMenuListener onMenuListener) {
        this.titleText = titleText;
        this.iconImage = iconImage;
        this.cardColor = cardColor;
        this.mOnMenuListener = onMenuListener;

        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view, mOnMenuListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleText.setText(titleText.get(position));
        holder.imageIcon.setImageResource(iconImage.get(position));

        int colorId = cardColor.get(position);
        int color = holder.cardView.getContext().getResources().getColor(colorId);
        holder.cardView.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return titleText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleText;
        private ImageView imageIcon;
        private CardView cardView;

        OnMenuListener onMenuListener;

        public ViewHolder(@NonNull View itemView, OnMenuListener onMenuListener) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            imageIcon = itemView.findViewById(R.id.imageIcon);
            cardView = itemView.findViewById(R.id.cardView);
            this.onMenuListener = onMenuListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMenuListener.onMenuClick(getAdapterPosition());
        }
    }

    public interface OnMenuListener {
        void onMenuClick(int position);
    }
}

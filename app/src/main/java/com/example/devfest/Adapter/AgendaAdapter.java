package com.example.devfest.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devfest.Model.AgendaItem;
import com.example.devfest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<AgendaItem> agendaItems;

    public AgendaAdapter(Context mcontext, ArrayList<AgendaItem> agenda) {
        context = mcontext;
        agendaItems = agenda;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.agenda_item, parent, false));
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.agendaStartTime.setText(agendaItems.get(position).getStartTime());
        holder.agendaEndTime.setText(agendaItems.get(position).getEndTime());
        holder.agendaTitle.setText(agendaItems.get(position).getTitle());
        holder.agendaPresenter.setText(agendaItems.get(position).getSpeakers());
        holder.agendaTag.setText(agendaItems.get(position).getComplexity());

        if (agendaItems.get(position).getPhotoUrl() != null) {
            Picasso.get().load(agendaItems.get(position).getPhotoUrl()).into(holder.agendaAvatar);
        } else {
            Picasso.get().load(R.drawable.gdg_logo).into(holder.agendaAvatar);
        }

        holder.agendaLine.setBackgroundColor(ContextCompat.getColor(context, randomColor()));
        holder.agendaLine.invalidate();

        if (position == agendaItems.size() - 1) {
            holder.agendaLine.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return agendaItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView agendaTitle, agendaPresenter, agendaTag, agendaStartTime, agendaEndTime;
        ImageView agendaAvatar;
        View agendaLine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            agendaTitle = itemView.findViewById(R.id.agendaTitle);
            agendaPresenter = itemView.findViewById(R.id.agendaPresenter);
            agendaTag = itemView.findViewById(R.id.agendaTag);
            agendaStartTime = itemView.findViewById(R.id.agendaStartTime);
            agendaEndTime = itemView.findViewById(R.id.agendaEndTime);
            agendaAvatar = itemView.findViewById(R.id.avatarImage);
            agendaLine = itemView.findViewById(R.id.agendaLine);
        }
    }
}

package com.example.devfest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devfest.Model.FaqItem;
import com.example.devfest.R;

import java.util.List;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder> {

    private List<FaqItem> faqList;

    public FaqAdapter(List<FaqItem> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.question.setText(faqList.get(position).getQuestion());
        holder.answer.setText(faqList.get(position).getAnswer());

        boolean isExpanded = faqList.get(position).isExpanded();
        holder.expandableList.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        if (isExpanded) {
            holder.arrowimage.setImageResource(R.drawable.ic_arrow_down_icon);
        } else {
            holder.arrowimage.setImageResource(R.drawable.ic_arrow_right);
        }

    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView question, answer;
        private ConstraintLayout expandableList;
        private ImageView arrowimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.questionFaq);
            answer = itemView.findViewById(R.id.answerFaq);
            expandableList = itemView.findViewById(R.id.expandableList);
            arrowimage = itemView.findViewById(R.id.arrowImage);

            question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FaqItem faq = faqList.get(getAdapterPosition());
                    faq.setExpanded(!faq.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            arrowimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FaqItem faq = faqList.get(getAdapterPosition());
                    faq.setExpanded(!faq.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

package com.example.sophisticatedquizzler.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sophisticatedquizzler.R;
import com.example.sophisticatedquizzler.adapter.PickCategoryRecyclerViewAdapter.ViewHolder;
import com.example.sophisticatedquizzler.data.model.SaveProgress;

import java.util.List;

public class PickCategoryRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{
    private List<SaveProgress> categoryList;
    private Context context;
    private RecyclerViewOnItemClick recyclerViewOnItemClick;


    public void getCategoryListItemString() {
        for (SaveProgress saveProgress: categoryList){
            Log.d("CATEGORYLIST:", "points:" + saveProgress.getPoints() +
                    "categoryName: " + saveProgress.getCategoryName() +
                    "saveProgress:" + saveProgress.getQuestionsCount());
        }
    }

    public PickCategoryRecyclerViewAdapter(
            Context context,
            List<SaveProgress> categoryList,
            RecyclerViewOnItemClick recyclerViewOnItemClick) {

        this.context = context;
        this.categoryList = categoryList;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.recyclerview_row,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title_textview.setText
                (categoryList.get(position).getCategoryName());

        holder.highScore_textview.setText(String.valueOf(
                categoryList.get(position).getPoints()));

        holder.progress_textview.setText(
                categoryList.get(position).getAnsweredQuestions() + "/" + categoryList.get(position).getQuestionsCount());


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void updateList(List<SaveProgress> saveProgressList) {
        this.categoryList = saveProgressList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title_textview;
        private TextView progress_textview;
        private TextView highScore_textview;
        private CardView category_cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_textview = itemView.findViewById(R.id.title_textview);
            progress_textview = itemView.findViewById(R.id.progress_textview);
            highScore_textview = itemView.findViewById(R.id.highScore_textview);
            category_cardview = itemView.findViewById(R.id.category_cardview);

            category_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewOnItemClick.itemClickCallback(
                            categoryList.get(getAdapterPosition()).getCategoryName(),
                            getAdapterPosition());
                }
            });
        }

    }

    public interface RecyclerViewOnItemClick {
        void itemClickCallback(String categoryName , int position);
    }
}

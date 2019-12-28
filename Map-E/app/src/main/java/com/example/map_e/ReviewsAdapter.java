package com.example.map_e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mRatings = new ArrayList<>();
    private ArrayList<String> mTime = new ArrayList<>();
    private ArrayList<String> mText = new ArrayList<>();
    private Context mContext;

    public ReviewsAdapter(Context context, ArrayList<String> names, ArrayList<String> ratings, ArrayList<String> times, ArrayList<String> texts) {
        mNames = names;
        mRatings = ratings;
        mTime = times;
        mText = texts;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(mNames.get(position));
        holder.rating.setText(mRatings.get(position));
        holder.text.setText(mText.get(position));
        holder.time.setText(mTime.get(position));

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView rating;
        TextView text;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            rating = itemView.findViewById(R.id.reviewer_rating);
            name = itemView.findViewById(R.id.reviewer_name);
            text = itemView.findViewById(R.id.review_text);
            time =  itemView.findViewById(R.id.review_time);
        }
    }
}

package com.example.moodtrack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moodtrack.dal.Affect;

import java.util.List;

public class MoodCalendarListAdapter extends RecyclerView.Adapter<MoodCalendarListAdapter.MoodDataViewHolder> {

    class MoodDataViewHolder extends RecyclerView.ViewHolder {
        private final TextView moodDataItemView;

        private MoodDataViewHolder(View itemView) {
            super(itemView);
            moodDataItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Affect> moodData; // cached copy of mood data

    MoodCalendarListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MoodDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MoodDataViewHolder(itemView);
    }

    /**
     * Format mood data (affect) item
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MoodDataViewHolder holder, int position) {
        if (moodData != null) {
            Affect current = moodData.get(position);
            String listItem = current.date + ": " + current.type;
            holder.moodDataItemView.setText(listItem);
        } else {
            // Covers the case of data not being ready yet.
            holder.moodDataItemView.setText("No mood data, yet.");
        }
    }

    void setMoodData(List<Affect> affects){
        moodData = affects;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // moodData has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (moodData != null) return moodData.size();
        else return 0;
    }
}
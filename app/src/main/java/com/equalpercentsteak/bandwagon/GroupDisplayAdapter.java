package com.equalpercentsteak.bandwagon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroupDisplayAdapter extends
        RecyclerView.Adapter<GroupDisplayAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.groupdisplay, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupDisplayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Group group = mGroups.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.groupNameTextView;
        textView.setText(group.getName());
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView groupNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.group_name);
        }
    }

    private List<Group> mGroups;

    public GroupDisplayAdapter(List<Group> groups){
        mGroups = groups;
    }

}

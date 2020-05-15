package com.equalpercentsteak.bandwagon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GroupDisplayAdapter extends
        RecyclerView.Adapter<GroupDisplayAdapter.ViewHolder> {

    private OnGroupListener mOnGroupListener;

    private ArrayList<Group> mGroups;
    private ArrayList<String> mGroupNames;

    public GroupDisplayAdapter(ArrayList<Group> groups, OnGroupListener onGroupListener){
        mGroups = groups;
        mOnGroupListener = onGroupListener;
    }

    public GroupDisplayAdapter(ArrayList<String> groupNames, OnGroupListener onGroupListener, String unused){
        mGroupNames = groupNames;
        mOnGroupListener = onGroupListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.groupdisplay, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView, mOnGroupListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupDisplayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String group = mGroupNames.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.groupNameTextView;
        textView.setText(group);

    }

    @Override
    public int getItemCount() {
        return mGroupNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView groupNameTextView;
        OnGroupListener onGroupListener;

        public ViewHolder(View itemView, OnGroupListener onGroupListener) {
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.group_name);
            this.onGroupListener=onGroupListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onGroupListener.onGroupClick(getAdapterPosition());
        }
    }

    public interface OnGroupListener{
        void onGroupClick(int position);
    }


}

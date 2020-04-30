package com.equalpercentsteak.bandwagon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.equalpercentsteak.bandwagon.R;

import java.util.List;

public class GroupDisplayAdapter extends
        RecyclerView.Adapter<GroupDisplayAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.groupdisplay, parent, false);

        // Return a new holder instance

//        ViewHolder viewHolder = new ViewHolder(itemView);
//        return viewHolder;
        //remove when fixed:
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //To-do
    }

    @Override
    public int getItemCount() {
        //To-do
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView groupNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            groupNameTextView = (TextView) itemView.findViewById(R.id.group_name);
        }
    }

    private List<Group> mGroups;

    public GroupDisplayAdapter(List<Group> groups){
        mGroups = groups;
    }

}

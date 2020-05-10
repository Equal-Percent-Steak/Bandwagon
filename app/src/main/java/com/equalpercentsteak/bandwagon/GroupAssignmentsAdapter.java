package com.equalpercentsteak.bandwagon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.equalpercentsteak.bandwagon.R;

import java.util.List;

public class GroupAssignmentsAdapter extends
        RecyclerView.Adapter<GroupAssignmentsAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupAssignmentsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Assignment assignment = mGroupAssignments.get(position);

        // Set item views based on your views and data model
        TextView titleTextView = viewHolder.TitleTextView;
        titleTextView.setText(assignment.getTitle());
        TextView descriptionTextView = viewHolder.DescriptionTextView;
        descriptionTextView.setText(assignment.getDescription());
//        if(check variable){
//            CheckAssignment.setChecked(true);
//        }
    }

    @Override
    public int getItemCount() {
        return mGroupAssignments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TitleTextView;
        public TextView DescriptionTextView;
        public CheckBox CheckAssignment;

        public ViewHolder(View itemView) {
            super(itemView);
            TitleTextView = (TextView)itemView.findViewById(R.id.titleTv);
            DescriptionTextView = (TextView)itemView.findViewById(R.id.descriptionTv);
            CheckAssignment=(CheckBox)itemView.findViewById(R.id.checkBox);
        }
    }

    private List<Assignment> mGroupAssignments;

    public GroupAssignmentsAdapter(List<Assignment> groupAssignments){
        mGroupAssignments = groupAssignments;
    }

}

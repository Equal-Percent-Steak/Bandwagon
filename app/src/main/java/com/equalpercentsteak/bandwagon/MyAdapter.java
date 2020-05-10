package com.equalpercentsteak.bandwagon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Assignment> assignments;
    ArrayList<Group> groups;
    ArrayList<String> groupNames;
    private MyHolder.OnAssignmentListener onAssignmentListener;

    /**
     * Constructs an adapter with an ArrayList of Assignments
     * @param c the context that forms the adapter
     * @param assignments  the assignments with which to populate the adapter
     * @param onAssignmentListener the onAssignmentListener for the adapter
     */
    public MyAdapter(Context c, ArrayList<Assignment> assignments, MyHolder.OnAssignmentListener onAssignmentListener) {
        this.c = c;
        this.assignments = assignments;
        this.onAssignmentListener=onAssignmentListener;
    }

    /**
     * Constructs an adapter with an ArrayList of groups
     * @param c the context that forms the adapter
     * @param groupNames the groups with which to populate the adapter
     * @param onAssignmentListener the onAssignmentListener for the adapter
     * @param unused makes this a different constructor
     */
    public MyAdapter(Context c, ArrayList<String> groupNames, MyHolder.OnAssignmentListener onAssignmentListener, String unused) {
        this.c = c;
        this.groupNames = groupNames;
        this.onAssignmentListener=onAssignmentListener;
    }

    /**
     * Constructs an adapter with an ArrayList of assignments
     * @param assignments the assignments to populate the adapter
     */
    public MyAdapter(ArrayList<Assignment> assignments) {
        this.assignments=assignments;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);


        return new MyHolder(view, onAssignmentListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.mTitle.setText(assignments.get(i).getTitle());
        myHolder.mDes.setText(assignments.get(i).getDescription());
//        if(check variable){
//            CheckAssignment.setChecked(true);
//        }

    }

    /**
     * Get the number of items in the adapter
     * @return the number of items in the adapter
     */
    @Override
    public int getItemCount() {
        return assignments.size();
    }
}

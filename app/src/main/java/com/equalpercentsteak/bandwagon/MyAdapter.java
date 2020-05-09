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


    public MyAdapter(Context c, ArrayList<Assignment> assignments, MyHolder.OnAssignmentListener onAssignmentListener) {
        this.c = c;
        this.assignments = assignments;
        this.onAssignmentListener=onAssignmentListener;
    }

    public MyAdapter(Context c, ArrayList<String> groupNames, MyHolder.OnAssignmentListener onAssignmentListener, String unused) {
        this.c = c;
        this.groupNames = groupNames;
        this.onAssignmentListener=onAssignmentListener;
    }

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


    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }
}

package com.equalpercentsteak.bandwagon;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView mTitle, mDes, mDate;
    public CheckBox checkAssignments;
    public OnAssignmentListener onAssignmentListener;
    public Group mGroup;
    public DatabaseReference groupFB;

    public MyHolder(@NonNull View itemView, OnAssignmentListener onAssignmentListener) {
        super(itemView);

        this.mTitle = itemView.findViewById(R.id.titleTv);
        this.mDes = itemView.findViewById(R.id.descriptionTv);
        this.mDate = itemView.findViewById(R.id.dateRVView);
        this.onAssignmentListener=onAssignmentListener;
        this.checkAssignments = itemView.findViewById(R.id.checkBox);

        checkAssignments.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO: work on this later Andrew
                groupFB = FirebaseDatabase.getInstance().getReference().child("groups").child(mGroup.getName()).child("assignments").child(mTitle.toString()).child("completedStudents");
            }
        }
        );
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onAssignmentListener.onAssignmentClick((getAdapterPosition()));
    }

    public interface OnAssignmentListener{
        void onAssignmentClick(int position);
    }
}

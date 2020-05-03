package com.equalpercentsteak.bandwagon;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mImageView;
    TextView mTitle, mDes;
    OnAssignmentListener onAssignmentListener;

    public MyHolder(@NonNull View itemView, OnAssignmentListener onAssignmentListener) {
        super(itemView);

        this.mTitle = itemView.findViewById(R.id.titleTv);
        this.mDes = itemView.findViewById(R.id.descriptionTv);
        this.onAssignmentListener=onAssignmentListener;

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

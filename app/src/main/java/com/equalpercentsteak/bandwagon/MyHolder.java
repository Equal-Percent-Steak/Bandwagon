package com.equalpercentsteak.bandwagon;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;


public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mTitle, mDes;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.imageIv);
        this.mTitle = itemView.findViewById(R.id.titleTv);
        this.mDes = itemView.findViewById(R.id.descriptionTv);

        itemView.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v){
            int position = getAdapterPosition();
            Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();

        }
        });
    }
}

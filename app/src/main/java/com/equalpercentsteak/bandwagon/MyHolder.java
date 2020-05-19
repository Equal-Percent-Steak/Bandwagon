package com.equalpercentsteak.bandwagon;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    /**
     * The TextViews in the holder of the title, description, and date
     */
    public TextView mTitle, mDes, mDate;
    /**
     * The checkBox that describes whether the user has completed the assignment
     */
    public CheckBox checkAssignments;
    /**
     * The onAssignmentListener which works with the onClick
     */
    public OnAssignmentListener onAssignmentListener;
    /**
     * The Group that the Assignment in the holder belongs to
     */
    public String mGroup;
    /**
     * Context
     */
    public Context c;
    //TODO Add firebase description
    /**
     *
     */
    public DatabaseReference groupFB;
    public DatabaseReference checkIfButtonPressed;
    private SoundPool pl;
    private int sound;


    /**
     * Constructs a MyHolder object that takes in a View object and an onAssignmentListener
     * @param itemView
     * @param onAssignmentListener the onAssignmentListener
     */
    public MyHolder(@NonNull final View itemView, OnAssignmentListener onAssignmentListener, Context c) {
        super(itemView);

        this.mTitle = itemView.findViewById(R.id.titleTv);
        this.mDes = itemView.findViewById(R.id.descriptionTv);
        this.mDate = itemView.findViewById(R.id.dateRVView);
        this.onAssignmentListener=onAssignmentListener;
        this.checkAssignments = itemView.findViewById(R.id.checkBox);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            pl = new SoundPool.Builder().setMaxStreams(5)
                    .setAudioAttributes(audioAttributes).build();
        }else{
            pl = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }

        sound = pl.load(c, R.raw.completed_sound, 1);

        checkAssignments.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                groupFB = FirebaseDatabase.getInstance().getReference().child("groups").child(mGroup).child("assignments").child(mTitle.getText().toString()).child("completedStudents").child(MainActivity.getUser().getId());
                if (checkAssignments.isChecked()){
                    groupFB.setValue(true);
                    itemView.jumpDrawablesToCurrentState();
                    pl.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        @Override
                        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                            soundPool.play(sampleId, 1.0f, 1.0f, 0, 0, 1.0f);
                        }
                    });                    Snackbar.make(buttonView, "Yay! You did it!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (!checkAssignments.isChecked()){
                    groupFB.removeValue();
                }
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

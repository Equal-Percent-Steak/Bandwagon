package com.equalpercentsteak.bandwagon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    /**
     * The Context of the adapter
     */
    Context c;
    /**
     * An ArrayList of assignments
     */
    ArrayList<Assignment> assignments;
    /**
     * An ArrayList of Groups
     */
    ArrayList<Group> groups;
    /**
     * An ArrayList of strings that has the groupNames
     */
    ArrayList<String> groupNames;
    /**
     * The onAssignmentListener for the onClick method
     */
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

    /**
     * Returns a MyHolder object based on a viewGroup and integer
     * @param viewGroup the viewGroup
     * @param i the integer
     * @return a MyHolder object
     */
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);


        return new MyHolder(view, onAssignmentListener);
    }

    /**
     * Updates the contents of the recycler view to display the task Description
     * @param myHolder the ViewHolder
     * @param i the position of the adapter
     */
    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        myHolder.mTitle.setText(assignments.get(i).getTitle());
        myHolder.mDes.setText(assignments.get(i).getDescription());
        myHolder.mGroup = assignments.get(i).getGroup();

        DatabaseReference checkIfButtonPressed = FirebaseDatabase.getInstance().getReference().child("groups").child(myHolder.mGroup.getName()).child("assignments").child(myHolder.mTitle.getText().toString()).child("completedStudents");
        checkIfButtonPressed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(MainActivity.getUser().getId())){
                    myHolder.checkAssignments.setChecked((boolean) dataSnapshot.child(MainActivity.getUser().getId()).getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        if(check variable){
//            CheckAssignment.setChecked(true);
//        }
        String date =  assignments.get(i).getDate();
        String time =  assignments.get(i).getTime();

        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);

        String year = date.substring(0, 4);
        String day = date.substring(6, 8);
        String month = "";
        String monthNum = date.substring(4, 6);
        if(monthNum.equals("01"))
            month = "January";
        else if(monthNum.equals("02"))
            month = "February";
        else if(monthNum.equals("03"))
            month = "March";
        else if(monthNum.equals("04"))
            month = "April";
        else if(monthNum.equals("05"))
            month = "May";
        else if(monthNum.equals("06"))
            month = "June";
        else if(monthNum.equals("07"))
            month = "July";
        else if(monthNum.equals("08"))
            month = "August";
        else if(monthNum.equals("09"))
            month = "September";
        else if(monthNum.equals("10"))
            month = "October";
        else if(monthNum.equals("11"))
            month = "November";
        else if(monthNum.equals("12"))
            month = "December";
        myHolder.mDate.setText(month + " " + day + ", " + year + " at " + hour + ":" + minute);

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

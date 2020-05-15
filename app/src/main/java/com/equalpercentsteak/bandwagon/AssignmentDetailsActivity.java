package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AssignmentDetailsActivity extends AppCompatActivity {

    TextView title, details, dueDetails, completedStudents;
    private static final String TAG = "AssignmentDetailsActivi";
    public DatabaseReference ref;
    private String mGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        final int[] studentsCompleted = new int[1];

        title = findViewById(R.id.taskName);
        String assignmentTitle = getIntent().getStringExtra("assignment");
        Log.d(TAG, "onCreate: title" + assignmentTitle);
        title.setText(assignmentTitle);

        completedStudents = findViewById(R.id.completedStudents);

        details = findViewById(R.id.details);
        String assignmentDetails = getIntent().getStringExtra("details");
        details.setText(assignmentDetails);

        mGroup = getIntent().getStringExtra("group");
        dueDetails = findViewById(R.id.dueDate);

        String time = getIntent().getStringExtra("time");
        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);

        String date = getIntent().getStringExtra("date");
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
        dueDetails.setText(month + " " + day + ", " + year + " at " + hour + ":" + minute);

        ref = FirebaseDatabase.getInstance().getReference().child("groups").child(mGroup).child("assignments").child(assignmentTitle);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ref.child("completedStudentsSize").setValue(dataSnapshot.child("completedStudents").getChildrenCount());
                studentsCompleted[0] = (int) dataSnapshot.child("completedStudents").getChildrenCount();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ref.child("completedStudentsSize").setValue(dataSnapshot.child("completedStudents").getChildrenCount());
                studentsCompleted[0] = (int) dataSnapshot.child("completedStudents").getChildrenCount();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ref.child("completedStudentsSize").setValue(dataSnapshot.child("completedStudents").getChildrenCount());
                studentsCompleted[0] = (int) dataSnapshot.child("completedStudents").getChildrenCount();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                ref.child("completedStudentsSize").setValue(dataSnapshot.child("completedStudents").getChildrenCount());
                studentsCompleted[0] = (int) dataSnapshot.child("completedStudents").getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        completedStudents.setText(studentsCompleted[0] + "");
    }
    public void performReturnSettings(View v) {
        Intent intent = new Intent(this,AccountSettings.class);
        startActivity(intent);
    }

    public void performGoToEditTask(View v) {
        Log.d(TAG, "performGoToEditTask: intent created");
        Intent intent = new Intent(this, EditTaskActivity.class);
        
        startActivity(intent);
    }

}
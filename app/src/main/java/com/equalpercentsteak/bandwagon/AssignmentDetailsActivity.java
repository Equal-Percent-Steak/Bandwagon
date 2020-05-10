package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AssignmentDetailsActivity extends AppCompatActivity {

    TextView title, details, dueDetails;
    private static final String TAG = "AssignmentDetailsActivi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        title = findViewById(R.id.taskName);
        String assignmentTitle = getIntent().getStringExtra("assignment");
        Log.d(TAG, "onCreate: title" + assignmentTitle);
        title.setText(assignmentTitle);

        details = findViewById(R.id.details);
        String assignmentDetails = getIntent().getStringExtra("details");
        details.setText(assignmentDetails);

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

    }
    public void performReturnSettings(View v) {
        Intent intent = new Intent(this,AccountSettings.class);
        startActivity(intent);
    }

}
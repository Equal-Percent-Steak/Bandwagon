package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AssignmentDetailsActivity extends AppCompatActivity {

    TextView title, details;
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
    }
    public void performReturnSettings(View v) {
        Intent intent = new Intent(this,AccountSettings.class);
        startActivity(intent);
    }
}
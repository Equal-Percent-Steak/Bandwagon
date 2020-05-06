package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class IndividualGroupPage extends MainActivity {

    ArrayList<Assignment> groupAssignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_group_page);

        // Lookup the recyclerview in activity layout
        RecyclerView rvGroupAssignments = (RecyclerView) findViewById(R.id.rvGroupAssignments);

        // Initialize contacts
        groupAssignments = Assignment.createAssignmentList();
        // Create adapter passing in the sample user data
        GroupAssignmentsAdapter adapter = new GroupAssignmentsAdapter(groupAssignments);
        // Attach the adapter to the recyclerview to populate items
        rvGroupAssignments.setAdapter(adapter);
        // Set layout manager to position the items
        rvGroupAssignments.setLayoutManager(new LinearLayoutManager(this));
    }

    public void performViewGroupMembers(View v) {
        Intent intent = new Intent(this,ClassMembers.class);
        startActivity(intent);
    }


}

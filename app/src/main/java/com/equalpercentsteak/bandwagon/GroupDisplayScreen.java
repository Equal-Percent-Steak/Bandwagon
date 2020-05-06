package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class GroupDisplayScreen extends MainActivity {

    ArrayList<Group> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_display_screen);
        // Lookup the recyclerview in activity layout
        RecyclerView rvGroups = (RecyclerView) findViewById(R.id.rvGroups);

        // Initialize contacts
        groups = Group.createGroupsList();
        // Create adapter passing in the sample user data
        GroupDisplayAdapter adapter = new GroupDisplayAdapter(groups);
        // Attach the adapter to the recyclerview to populate items
        rvGroups.setAdapter(adapter);
        // Set layout manager to position the items
        rvGroups.setLayoutManager(new LinearLayoutManager(this));
    }
}

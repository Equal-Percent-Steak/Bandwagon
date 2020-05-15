package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import java.util.ArrayList;

public class ClassMembers extends MainActivity {

    ArrayList<User> users;

    /**
     * Creates the activity that lists all of the users in each class.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_members);

        // Find the recyclerview in activity layout
        RecyclerView rvUsers = (RecyclerView)findViewById(R.id.rvUsers);

        // Initialize contacts
        //TODO:Not implemented correctly
        // Create adapter passing in the sample user data
        UserAdapter adapter = new UserAdapter(users);
        // Attach the adapter to the recyclerview to populate items
        rvUsers.setAdapter(adapter);
        // Set layout manager to position the items
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Returns to the details page of the individual group
     * @param v
     */
    public void performViewClassAssignments(View v) {
        Intent intent = new Intent(this,IndividualGroupPage.class);
        startActivity(intent);
    }
}

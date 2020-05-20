package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * This class corresponds to the individual group page which is opened upon clicking on a group from the groups page.
 */
public class IndividualGroupPage extends MainActivity {

    /**
     * the name of the group to be displayed at the top of the screen
     */
    private TextView groupName;

    /**
     * the reference to Firebase to obtain the group to display
     */
    private DatabaseReference mGroups;

    /**
     * the ArrayList of Assignments belonging to a specific group
     */
    private ArrayList<Assignment> list;

    /**
     * the Recycler View which displays the assignments specific to an individual group
     */
    private RecyclerView rvGroupAssignments;

    /**
     * Creates an instance of the Individual Group Page
     * @param savedInstanceState the saved Individual Group Page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_group_page);

        // Lookup the recyclerview in activity layout
        rvGroupAssignments = findViewById(R.id.rvGroupAssignments);
        // Set layout manager to position the items
        rvGroupAssignments.setLayoutManager(new LinearLayoutManager(this));


        groupName = findViewById(R.id.groupName);
        groupName.setText(getIntent().getStringExtra("name"));

        mGroups = FirebaseDatabase.getInstance().getReference().child("groups").child(getIntent().getStringExtra("name")).child("assignments");
        mGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot assignments: dataSnapshot.getChildren())
                {
                    Assignment a = assignments.getValue(Assignment.class);
                    list.add(a);
                }
                // Create adapter passing in the data retrieved from Firebase
                MyAdapter myAdapter = new MyAdapter(IndividualGroupPage.this,list,IndividualGroupPage.this);
                // Attach the adapter to the recyclerview to populate items
                rvGroupAssignments.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(IndividualGroupPage.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * changes the view to the Group Members page
     * @param v the button which changes the view
     */
    public void performViewGroupMembers(View v) {
        Intent intent = new Intent(this,ClassMembers.class);
        intent.putExtra("group_name",getIntent().getStringExtra("name"));
        startActivity(intent);
    }


}

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

    private TextView groupName;
    private DatabaseReference mGroups;
    private ArrayList<Assignment> list;
    private RecyclerView rvGroupAssignments;

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

    public void performViewGroupMembers(View v) {
        Intent intent = new Intent(this,ClassMembers.class);
        intent.putExtra("group_name",getIntent().getStringExtra("name"));
        startActivity(intent);
    }


}

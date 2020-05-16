package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupDisplayScreen extends MainActivity implements GroupDisplayAdapter.OnGroupListener {

//    private ArrayList<Group> groups;
    private DatabaseReference mGroups;
    private ArrayList<String> list;
    private RecyclerView rvGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_display_screen);
        // Lookup the recyclerview in activity layout
        rvGroups = findViewById(R.id.rvGroups);
        rvGroups.setLayoutManager(new LinearLayoutManager(this));

        mGroups = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.getUser().getId()).child("classes");

        mGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot groups: dataSnapshot.getChildren())
                {
                    String gName = groups.getValue().toString();
                    list.add(gName);
                }
                GroupDisplayAdapter groupDisplayAdapter = new GroupDisplayAdapter(list,GroupDisplayScreen.this, "");
                rvGroups.setAdapter(groupDisplayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(GroupDisplayScreen.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize contacts
//        groups = Group.createGroupsList();
//        // Create adapter passing in the sample user data
//        GroupDisplayAdapter adapter = new GroupDisplayAdapter(groups);
//        // Attach the adapter to the recyclerview to populate items
//        rvGroups.setAdapter(adapter);
//        // Set layout manager to position the items
//        rvGroups.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onGroupClick(int position) {
        //list.get(position);
        Intent intent = new Intent(this, IndividualGroupPage.class);
        intent.putExtra("name", list.get(position));
        startActivity(intent);
    }
}

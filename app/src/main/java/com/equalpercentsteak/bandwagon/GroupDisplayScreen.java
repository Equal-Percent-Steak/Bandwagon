package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupDisplayScreen extends MainActivity {

    private ArrayList<Group> groups;
    private DatabaseReference mGroups;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_display_screen);
        // Lookup the recyclerview in activity layout
        final RecyclerView rvGroups = (RecyclerView) findViewById(R.id.rvGroups);

        mGroups = FirebaseDatabase.getInstance().getReference().child("groups");
        mGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<String>();
                for(DataSnapshot groups: dataSnapshot.getChildren())
                {
                    String g = groups.child("group_name").getValue().toString();
                    list.add(g);
                }
                myAdapter = new MyAdapter(GroupDisplayScreen.this,list,GroupDisplayScreen.this,"");
                rvGroups.setAdapter(myAdapter);
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
}

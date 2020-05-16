package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ClassMembers extends MainActivity {

    private DatabaseReference mUsers;
    private ArrayList<User> list;
    /**
     * Creates the activity that lists all of the users in each class.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_members);

        TextView title = findViewById(R.id.groupName);
        title.setText(getIntent().getStringExtra("group_name"));

        // Find the recyclerview in activity layout
        final RecyclerView rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize contacts
        //TODO:Not implemented correctly
        // Create adapter passing in the sample user data
        mUsers = FirebaseDatabase.getInstance().getReference().child("groups").child(getIntent().getStringExtra("group_name")).child("members");
        mUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot userList: dataSnapshot.getChildren())
                {
                    User u = userList.getValue(User.class);
                    list.add(u);
                }
                UserAdapter adapter = new UserAdapter(list);
                // Attach the adapter to the recyclerview to populate items
                rvUsers.setAdapter(adapter);
                // Set layout manager to position the items
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ClassMembers.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Returns to the details page of the individual group
     * @param v
     */
    public void performViewClassAssignments(View v) {
        Intent intent = new Intent(this,IndividualGroupPage.class);
        intent.putExtra("name", getIntent().getStringExtra("group_name"));
        startActivity(intent);
    }
}

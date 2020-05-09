package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddUser extends MainActivity {

    private ArrayList<String> list;
    private DatabaseReference mGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        final Spinner groupMenu = (Spinner) findViewById(R.id.groupChoice);

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
                ArrayAdapter<String> groupMenuAdapter = new ArrayAdapter<String>(AddUser.this,
                        android.R.layout.simple_list_item_1, list);
                groupMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                groupMenu.setAdapter(groupMenuAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AddUser.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onClick(View v){
        EditText username = findViewById(R.id.username);
        Spinner groupChoice = findViewById(R.id.groupChoice);
        if( TextUtils.isEmpty(username.getText())
                ||groupChoice.getSelectedItem()==null){
            Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show();
            username.setError( "All fields must be filled in" );
        }
        else {
            EditText usernameInput = (EditText) findViewById(R.id.username);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference users = database.getReference("users");
            //TODO:Add members to the correct group
            users.child(usernameInput.getText().toString()).child("username").setValue(usernameInput.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}

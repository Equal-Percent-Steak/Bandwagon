package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddUser extends MainActivity {

    /**
     * the list of groups to be displayed in the spinner for the user
     */
    private ArrayList<String> list;
    /**
     * the list of groups stored in Firebase specific to the user
     */
    private DatabaseReference mGroups;
    /**
     * Creates the AddUser activity that allows a user to add another user to a group.
     * When the AddUser activity is created, the list of groups is pulled from Firebase so that the group choice spinner is adaptive.
     * @param savedInstanceState the saved state of the AddUser class
     */

    private ArrayList classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        final Spinner groupMenu = (Spinner) findViewById(R.id.groupChoice);

        mGroups = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.getUser().getId()).child("classes");
        mGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<String>();
                for(DataSnapshot groups: dataSnapshot.getChildren())
                {
                    String g = groups.getValue().toString();
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

    /**
     * When the "Done" button is clicked, the onClick listener is called, which ensures that all fields have been checked and then sends the new user data to Firebase.
     * @param v
     */
    public void onClick(View v){
        EditText username = findViewById(R.id.username);
        final Spinner groupChoice = findViewById(R.id.groupChoice);

        if( TextUtils.isEmpty(username.getText())){
            Toast.makeText(this, "Please enter a user to invite", Toast.LENGTH_SHORT).show();
            username.setError( "Username must be entered" );
        }

        else if(groupChoice.getSelectedItem()==null){
            Toast.makeText(this, "Please select a group", Toast.LENGTH_SHORT).show();
            TextView errorText = (TextView)groupChoice.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Group must be selected");
        }

        else {
            final EditText usernameInput = (EditText) findViewById(R.id.username);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference users = database.getReference("users").child(usernameInput.getText().toString());
            final DatabaseReference groups = database.getReference("groups");

            users.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild("classes")){
                        classes = (ArrayList<String>) dataSnapshot.child("classes").getValue();
                        if(!classes.contains(groupChoice.getSelectedItem().toString())){
                            classes.add(groupChoice.getSelectedItem().toString());
                            users.child("classes").setValue(classes);
                        }

                        User u = new User(dataSnapshot.child("username").getValue(String.class),usernameInput.getText().toString());
                        groups.child(groupChoice.getSelectedItem().toString()).child("members").child(usernameInput.getText().toString()).setValue(u);
                    } else{
                        classes = new ArrayList<>();
                        classes.add(groupChoice.getSelectedItem().toString());
                        users.child("classes").setValue(classes);

                        User u = new User(dataSnapshot.child("username").getValue(String.class),usernameInput.getText().toString());
                        groups.child(groupChoice.getSelectedItem().toString()).child("members").child(usernameInput.getText().toString()).setValue(u);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            //TODO:Add members to the correct group
//            users.child(usernameInput.getText().toString()).child("username").setValue(usernameInput.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}

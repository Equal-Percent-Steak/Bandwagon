package com.equalpercentsteak.bandwagon;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CreateGroup extends MainActivity {

    /**
     * The group that is created by the method
     */
    private Group group;

    private ArrayList<String>[] arr = new ArrayList[1];
    /**
     * Creates and opens an instance of the Create Group screen
     * @param savedInstanceState the saved Create Group screen
     */

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference groups = database.getReference("groups");

    private ValueEventListener listener;

    /**
     * DatabaseReference of the users
     */
    private final DatabaseReference users = database.getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("groups");
//        ref.addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        ArrayList<String> groupList = new ArrayList<>();
//
//                        //Get map of users in datasnapshot
//                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
//                            groupList.add(dsp.getKey()); //add result into array list
//
//                        }
////TODO: FIX
//                        System.out.println(groupList.toString());
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });
    }

    /**
     * Checks to make sure all fields have been entered, adds the new group to Firebase, and switches the view back to the home screen
     * @param v the object to be clicked
     */
    public void onClick(View v){
        final EditText enterGroup = findViewById(R.id.groupName);
        EditText enterDetails = findViewById(R.id.groupDescription);

        if( TextUtils.isEmpty(enterGroup.getText())){
            Toast.makeText(this, "Please enter a group name", Toast.LENGTH_SHORT).show();
            enterGroup.setError( "Group name must be entered" );
        }

        else if(TextUtils.isEmpty(enterDetails.getText())) {
            Toast.makeText(this, "Please enter group details", Toast.LENGTH_SHORT).show();
            enterGroup.setError( "Group details must be entered" );
        }

        else {
            group = new Group(enterGroup.getText().toString(), MainActivity.getUser());

            groups.child(enterGroup.getText().toString()).child("group_name").setValue(enterGroup.getText().toString());
            groups.child(enterGroup.getText().toString()).child("description").setValue(enterDetails.getText().toString());
            groups.child(enterGroup.getText().toString()).child("members").child(MainActivity.keyId).setValue(MainActivity.getUser());
            groups.child(enterGroup.getText().toString()).child("assignments").setValue(group.getAssignments());

            listener = users.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<String> groupArr;
                    if(dataSnapshot.child(MainActivity.getUser().getId()).child("classes").exists()){
                        groupArr = (ArrayList<String>) dataSnapshot.child(MainActivity.getUser().getId()).child("classes").getValue();
                    } else{
                        groupArr = new ArrayList<>();
                    }
                    groupArr.add(enterGroup.getText().toString());
                    //        System.out.println(getListOfGroups());
                    arr[0] = groupArr;
                    users.child(MainActivity.getUser().getId()).child("classes").setValue(arr[0]);
                    performReturnHome();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });

        }
    }

    /**
     * Switches the activity to the home screen
     */
    public void performReturnHome() {
        users.removeEventListener(listener);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

//    public ArrayList<String> getListOfGroups(){
//        final ArrayList<String> groupList = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("groups");
//        ref.addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Get map of users in datasnapshot
//                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
//                            groupList.add(dsp.getKey()); //add result into array list
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });
//        return groupList;
//    }
}

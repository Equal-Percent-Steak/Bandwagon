package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class CreateGroup extends MainActivity {

    private Group group;

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

    public void onClick(View v){
        EditText enterGroup = findViewById(R.id.groupName);
        EditText enterDetails = findViewById(R.id.groupDescription);
        if( TextUtils.isEmpty(enterGroup.getText())||TextUtils.isEmpty(enterDetails.getText())){
            Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show();
            enterGroup.setError( "All fields must be filled in" );
        }
        else {
            EditText groupName = (EditText) findViewById(R.id.groupName);
            EditText description = (EditText) findViewById(R.id.groupDescription);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference groups = database.getReference("groups");

            group = new Group(groupName.getText().toString(), MainActivity.getUser());

            groups.child(groupName.getText().toString()).child("group_name").setValue(groupName.getText().toString());
            groups.child(groupName.getText().toString()).child("description").setValue(description.getText().toString());
            groups.child(groupName.getText().toString()).child("members").child(MainActivity.keyId).setValue(MainActivity.getUser());
            groups.child(groupName.getText().toString()).child("assignments").setValue(group.getAssignments());

//        System.out.println(getListOfGroups());
            performReturnHome();
        }
    }

    public void performExitCreateNewGroup(View v){
        Intent intent = new Intent(this, CreateNewTaskOrGroupMenu.class);
        startActivity(intent);
    }

    public void performReturnHome() {
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

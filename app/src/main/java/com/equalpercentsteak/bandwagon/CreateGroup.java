package com.equalpercentsteak.bandwagon;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateGroup extends MainActivity {

    /**
     * The group that is created by the method
     */
    private Group group;

    /**
     * Creates and opens an instance of the Create Group screen
     * @param savedInstanceState the saved Create Group screen
     */
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
        EditText enterGroup = findViewById(R.id.groupName);
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
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference groups = database.getReference("groups");

        group = new Group(enterGroup.getText().toString(), MainActivity.getUser());
//TODO: this is not modeled correctly oops
        groups.child(enterGroup.getText().toString()).child("group_name").setValue(enterGroup.getText().toString());
        groups.child(enterGroup.getText().toString()).child("description").setValue(enterDetails.getText().toString());
        groups.child(enterGroup.getText().toString()).child("members").child(MainActivity.keyId).setValue(MainActivity.getUser());
        groups.child(enterGroup.getText().toString()).child("assignments").setValue(group.getAssignments());

//        System.out.println(getListOfGroups());
            performReturnHome();
        }
    }

    /**
     * Switches the activity to the home screen
     */
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

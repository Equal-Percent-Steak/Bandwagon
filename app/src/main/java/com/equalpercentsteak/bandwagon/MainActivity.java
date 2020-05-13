package com.equalpercentsteak.bandwagon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MyHolder.OnAssignmentListener {
    private static final String TAG = "MainActivity";

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;
//    private String mUsername;
    public static final int RC_SIGN_IN = 1;
    private DatabaseReference mDatabase;
    private static User user;
    public static String keyId;
    private DatabaseReference mGroups;
    private ArrayList<Assignment> list;


    /**
     * This method is called when the activity is started.
     * It checks if the user is logged in and also lists all of the assignments the user has on one screen.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializes FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        // Initializes recycler view
        mRecyclerView = findViewById(R.id.recyclerView);
        // Initializes linear layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // The listener checks if the user is logged in, and if the user is not, it forces the user to sign in
        mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser userFB = firebaseAuth.getCurrentUser();
                if (userFB != null){
//                    onSignedInInitialize(userFB.getDisplayName());
                    user = new User(userFB.getEmail(),userFB.getUid());
                    keyId = userFB.getUid();
                } else if (userFB == null){
                    //not signed in
//                    onSignedOutCleanup();
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build());
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                    user = new User(userFB.getEmail(),userFB.getUid());
                    keyId = userFB.getUid();
                    addUserToDB(userFB);
                }
            }
        };

        // Populates the recycler view with the list of all of the tasks from Firebase
        mGroups = FirebaseDatabase.getInstance().getReference().child("groups");
        mGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Assignment>();
                for(DataSnapshot groups: dataSnapshot.getChildren())
                {
                    DataSnapshot individualAssignments = groups.child("assignments");
                    for(DataSnapshot assignments: individualAssignments.getChildren()){
                        Assignment a = assignments.getValue(Assignment.class);
                        list.add(a);
                    }
                }
                myAdapter = new MyAdapter(MainActivity.this,list,MainActivity.this);
                mRecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settingsMenu:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sign_out_menu:
                Toast.makeText(this, "Sign out selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.createNewMenu:
                Toast.makeText(this, "Create New selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.groupMenu:
                Toast.makeText(this, "Create New Group selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.taskMenu:
                Toast.makeText(this, "Create New Task selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {
       super.onResume();
       mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
    }

    private void addUserToDB(FirebaseUser currentUser){
        mDatabase.child(keyId).setValue(user);
    }

    public void performEnterSettings(MenuItem item){
        Intent intent = new Intent(this,AccountSettings.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    public void performEnterNewTask(MenuItem item){
        Intent intent = new Intent(this,CreateTask.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }
    public void performEnterNewGroup(MenuItem item){
        Intent intent = new Intent(this,CreateGroup.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    public void performEnterNewUser(MenuItem item){
        Intent intent = new Intent(this,AddUser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    public void performEnterGroups(MenuItem item){
        Intent intent = new Intent(this,GroupDisplayScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    public void performReturnHome(MenuItem item) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    /**
     * @return the current user
     */
    public static User getUser(){
        return user;
    }

    /**
     * After the assignment is clicked, this method is called, and it passes along the data of the
     * assignment to the individual activity page.
     * @param position the position on the Adapter
     */
    @Override
    public void onAssignmentClick(int position) {
        Log.d(TAG, "onAssignmentClick: clicked" + position);

        Intent intent = new Intent(this, AssignmentDetailsActivity.class);
        intent.putExtra("assignment", list.get(position).getTitle());
        intent.putExtra("details", list.get(position).getDescription());
        intent.putExtra("date", list.get(position).getDate());
        intent.putExtra("time", list.get(position).getTime());
        startActivity(intent);
    }

    public void check(View v){
        CheckBox completedCheck = (CheckBox)findViewById(R.id.checkBox);
        if(completedCheck.isChecked()){
            //Change boolean for specific user
        }
    }
}



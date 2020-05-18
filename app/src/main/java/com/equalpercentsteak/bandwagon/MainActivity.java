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
    private DatabaseReference mUsers;
    private ArrayList<String> namesOfAllowedGroups;
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
                    loadGroups();
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
                    loadGroups();
                }
                list = new ArrayList<Assignment>();

            }
        };


    }

    public void loadGroups(){
        mUsers = FirebaseDatabase.getInstance().getReference().child("users").child(getUser().getId());
        mUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namesOfAllowedGroups = new ArrayList<>();
                long children = dataSnapshot.child("classes").getChildrenCount();
                for(DataSnapshot groups: dataSnapshot.child("classes").getChildren()){
                    namesOfAllowedGroups.add((String) groups.getValue());
                }
                loadAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loadAdapter(){
        if(namesOfAllowedGroups != null){
            for(String groups: namesOfAllowedGroups){
                // Populates the recycler view with the list of all of the tasks from Firebase
                mGroups = FirebaseDatabase.getInstance().getReference().child("groups").child(groups);
                mGroups.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        DataSnapshot individualAssignments = dataSnapshot.child("assignments");
                        for(DataSnapshot assignments: individualAssignments.getChildren()){
                            Assignment a = assignments.getValue(Assignment.class);
                            list.add(a);
                        }

                        myAdapter = new MyAdapter(MainActivity.this,list,MainActivity.this);
                        mRecyclerView.setAdapter(myAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Something is wrong" + getUser().getId(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    /**
     * Creates an instance of the menu
     * @param menu the menu to be applied to all screens for ease of navigation
     * @return the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.settingsMenu:
//                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.sign_out_menu:
//                Toast.makeText(this, "Sign out selected", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.createNewMenu:
//                Toast.makeText(this, "Add New selected", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.groupMenu:
//                Toast.makeText(this, "Create New Group selected", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.taskMenu:
//                Toast.makeText(this, "Create New Task selected", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

    @Override
    protected void onResume() {
       super.onResume();
       mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
    }

    private void addUserToDB(FirebaseUser currentUser){
        mDatabase.child(keyId).setValue(user);
    }

    /**
     * When the user clicks the settings button from the menu, the Settings page is opened
     * @param item the item selected from the menu
     */
    public void performEnterSettings(MenuItem item){
        Intent intent = new Intent(this,AccountSettings.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    public void performEnterSignOut(MenuItem item){

        FirebaseAuth.getInstance().signOut();
    }

    /**
     * When the user clicks the create task button from the menu, the Create Task page is opened
     * @param item the item selected from the menu
     */
    public void performEnterNewTask(MenuItem item){
        Intent intent = new Intent(this,CreateTask.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    /**
     * When the user clicks the create group button from the menu, the Create Group page is opened
     * @param item the item selected from the menu
     */
    public void performEnterNewGroup(MenuItem item){
        Intent intent = new Intent(this,CreateGroup.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    /**
     * When the user clicks the add user button from the menu, the Add User page is opened
     * @param item the item selected from the menu
     */
    public void performEnterNewUser(MenuItem item){
        Intent intent = new Intent(this,AddUser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    /**
     * When the user clicks the Groups button from the menu, the Groups page is opened
     * @param item the item selected from the menu
     */
    public void performEnterGroups(MenuItem item){
        Intent intent = new Intent(this,GroupDisplayScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    /**
     * When the user clicks the Groups button from the menu, the Groups page is opened
     * @param item the item selected from the menu
     */
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
        intent.putExtra("group",list.get(position).getGroup());
        startActivity(intent);
    }
}



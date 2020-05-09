package com.equalpercentsteak.bandwagon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MyHolder.OnAssignmentListener {
    private static final String TAG = "MainActivity";

    private FirebaseFirestore db;
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;
//    private String mUsername;
    public static final int RC_SIGN_IN = 1;
    private DatabaseReference mDatabase;
    private static User user;
    public ArrayList<Assignment> assignments = Assignment.getMyList();
    public static String keyId;
    private DatabaseReference mGroups;
    private ArrayList<Assignment> list;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        //Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");

//        setContentView(R.layout.fragment_dashboard);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        myAdapter = new MyAdapter(this, assignments, this);
        mRecyclerView.setAdapter(myAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser userFB = firebaseAuth.getCurrentUser();
                if (userFB != null){
//                    onSignedInInitialize(userFB.getDisplayName());
                    user = new User(userFB.getEmail());
                    keyId = userFB.getUid();
                } else if (userFB == null){
                    //not signed in
////                    onSignedOutCleanup();
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build());
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                    user = new User(userFB.getEmail());
                    keyId = userFB.getUid();
                    addUserToDB(userFB);
                }
            }
        };

        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });


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

//    @Override
//    protected void onStart(){
//        super.onStart();
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(myAdapter);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else{
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
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


//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.sign_out_menu:
//                AuthUI.getInstance().signOut(this);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


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

    public static User getUser(){
        return user;
    }

    @Override
    public void onAssignmentClick(int position) {
        Log.d(TAG, "onAssignmentClick: clicked" + position);

        Intent intent = new Intent(this, AssignmentDetailsActivity.class);
        intent.putExtra("assignment", list.get(position).getTitle());
        intent.putExtra("details", list.get(position).getDescription());
        startActivity(intent);
    }
}



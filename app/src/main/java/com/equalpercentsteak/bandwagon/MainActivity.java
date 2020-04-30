package com.equalpercentsteak.bandwagon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FirebaseFirestore db;
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;
    private String mUsername;
    public static final int RC_SIGN_IN = 1;
    public ArrayList<Assignment> assignments = new ArrayList<>();
    private DatabaseReference mDatabase;
    private static User user;



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
        DatabaseReference myRef = database.getReference("message");

//        setContentView(R.layout.fragment_dashboard);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser userFB = firebaseAuth.getCurrentUser();
                if (userFB != null){
//                    onSignedInInitialize(userFB.getDisplayName());
                    user = new User(userFB.getEmail());
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
                    addUserToDB(userFB);
                }
            }
        };

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
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
    public ArrayList<Assignment> getMyList(){

        Assignment m = new Assignment();
        m.setTitle("News Feed");
        m.setDescription("This is a newsfeed Description");
//        m.setImg(R.drawable.ic_android_black_24dp);
        assignments.add(m);

//        Assignment s = new Assignment();
//        s.setTitle("Food");
//        s.setDescription("This is a different Description");
//        s.setImg(R.drawable.ic_android_black_24dp);
//        assignments.add(s);

        Assignment t = new Assignment();
        t.setTitle("ASSIGNMENT TITLE");
        t.setDescription("This is a description (Add Date here?)");
//        t.setImg(R.drawable.ic_android_black_24dp);
        assignments.add(t);

        Assignment q = new Assignment();
        q.setTitle("Another Example Title");
        q.setDescription("This is another Description");
//        q.setImg(R.drawable.ic_android_black_24dp);
        assignments.add(q);

        Assignment a = new Assignment();
        a.setTitle("Another Example Title");
        a.setDescription("This is another Description");
//        a.setImg(R.drawable.ic_android_black_24dp);
        assignments.add(a);

        Assignment b = new Assignment();
        b.setTitle("Another Example Title");
        b.setDescription("This is another Description");
//        b.setImg(R.drawable.ic_android_black_24dp);
        assignments.add(b);

        return assignments;

    }


    @Override
    protected void onResume() {
       super.onResume();
       mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
//    }

    private void addUserToDB(FirebaseUser currentUser){
        String keyId = currentUser.getUid();
        mDatabase.child(keyId).setValue(user);
    }

//    private void onSignedInInitialize(String username){
//        mUsername = username;
//    }

//    private void onSignedOutCleanup(){
//        mUsername = "";
//    }

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

    public void performReturnHome(MenuItem item) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    public static User getUser(){
        return user;
    }

}



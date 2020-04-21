package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateTask extends AppCompatActivity {

    FirebaseFirestore db;
    final String TAG = "CreateTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        Spinner groupMenu = (Spinner) findViewById(R.id.groupChoice);

        ArrayAdapter<String> groupMenuAdapter = new ArrayAdapter<String>(CreateTask.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(
                R.array.dropdownNames));
        groupMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupMenu.setAdapter(groupMenuAdapter);
        initFirestore();
    }

    private void initFirestore(){
        db = FirebaseFirestore.getInstance();
        DocumentReference dr = db.collection("groups").document("0KouSLYueZjr8L5lzyaA");
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    public void addTaskToFB(View v) {
        EditText taskName = findViewById(R.id.enterTask);
        EditText dueDate = findViewById(R.id.dueDate);
        Spinner groupChoice = findViewById(R.id.groupChoice);
        EditText description = findViewById(R.id.enterDetails);

        Map<String, Object> task = new HashMap<>();
        task.put("task_name",taskName.getText().toString());
        task.put("due_date",dueDate.getText().toString());
        task.put("group",groupChoice.getSelectedItem().toString());
        task.put("description",description.getText().toString());

        db.collection("groups").add(task);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void performExitCreateNewTask(View v){
        Intent intent = new Intent(this, CreateNewTaskOrGroupMenu.class);
        startActivity(intent);
    }

    public void performReturnHome(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

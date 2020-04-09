package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class createTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        Spinner groupMenu = (Spinner) findViewById(R.id.groupChoice);

        ArrayAdapter<String> groupMenuAdapter = new ArrayAdapter<String>(createTask.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(
                R.array.dropdownNames));
        groupMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupMenu.setAdapter(groupMenuAdapter);
    }

    public void addTaskToFB(View v) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
    }
}

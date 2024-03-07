package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    FirebaseFirestore database;
    TextView tQK;
    String strKQ = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseFirestore.getInstance();
        tQK = findViewById(R.id.tKQ);

        //insertData();
        //update();
        delete();
        //ArrayList<Todo> todos = select();
    }

    void insertData() {
        String id = UUID.randomUUID().toString();
        Todo todo = new Todo(id, "title 11", "content 11");
        database.collection("TODO")
                .document(id)
                .set(todo.convertHansh())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    void update() {
        String id = "469d946e-9392-4208-a434-8a7e897ec34d";
        Todo todo = new Todo(id, "title 11 update", "content 11 update");
        database.collection("TODO").document(id).update(todo.convertHansh()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void delete() {
        String id = "469d946e-9392-4208-a434-8a7e897ec34d";//copy id
        database.collection("TODO").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ArrayList<Todo> select() {
        ArrayList<Todo> list = new ArrayList<>();
        database.collection("TODO").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    strKQ = "";
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        Todo t = doc.toObject(Todo.class);//Chuyển dữ liệu doc sang TODO
                        list.add(t);
                        strKQ += "id" + t.getId() + "\n";
                        strKQ += "title" + t.getTitle() + "\n";
                        strKQ += "content" + t.getContent() + "\n";
                    }
                    tQK.setText(strKQ); // Hiển thị dữ liệu trên TextView
                } else {
                    Toast.makeText(context, "Select Thất bại", Toast.LENGTH_SHORT).show();
                    Log.e("Firebase", "Error getting documents: ", task.getException());
                }
            }
        });
        return list;
    }
}

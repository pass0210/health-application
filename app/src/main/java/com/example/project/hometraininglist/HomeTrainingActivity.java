package com.example.project.hometraininglist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

public class HomeTrainingActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.hometraining_1, R.drawable.hometraining_2, R.drawable.hometraining_3,
            R.drawable.hometraining_4, R.drawable.hometraining_5, R.drawable.hometraining_6,
            R.drawable.hometraining_7, R.drawable.hometraining_8, R.drawable.hometraining_9, R.drawable.hometraining_10};


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_training_list);

        recyclerView = findViewById(R.id.homecycle);

        s1 = getResources().getStringArray(R.array.hometraining);
        s2 = getResources().getStringArray(R.array.homedescription);

        HomeTrainingAdapter myAdapter = new HomeTrainingAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

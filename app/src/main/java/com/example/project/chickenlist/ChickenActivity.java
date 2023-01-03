package com.example.project.chickenlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.hometraininglist.HomeTrainingAdapter;

public class ChickenActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    String s1[], s2[], s3[];
    int images[] = {R.drawable.chicken_1, R.drawable.chicken_2, R.drawable.chicken_3,
            R.drawable.chicken_4, R.drawable.chicken_5, R.drawable.chicken_6,
            R.drawable.chicken_7, R.drawable.chicken_8, R.drawable.chicken_9, R.drawable.chicken_10};
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chicken_list);

        recyclerView = findViewById(R.id.chickencycle);

        s1 = getResources().getStringArray(R.array.chicken);
        s2 = getResources().getStringArray(R.array.chickendescription);
        s3 = getResources().getStringArray(R.array.rating);

        ChickenAdapter myAdapter = new ChickenAdapter(this, s1, s2, s3, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

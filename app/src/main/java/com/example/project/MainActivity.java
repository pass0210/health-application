package com.example.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.chickenlist.ChickenActivity;
import com.example.project.hometraininglist.HomeTrainingActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.calendar.CalendarWithList;
import com.example.project.excersiselist.HealthListActivity;
import com.example.project.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView naviMenu;
    private TextView userInformation;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigationView);

        naviMenu = findViewById(R.id.naviMenu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.diary:
                        if (userEmail == null || userEmail.equals("unknown")) {
                            Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(it, 0);
                        } else {
                            drawerLayout.closeDrawers();
                            Intent it = new Intent(MainActivity.this, CalendarWithList.class);
                            it.putExtra("UserEmail", userEmail);
                            startActivity(it);
                        }
                        return true;
                    case R.id.healthList:
                        drawerLayout.closeDrawers();
                        Intent in = new Intent(MainActivity.this, HealthListActivity.class);
                        startActivity(in);
                        return true;
                    case R.id.chicken:
                        drawerLayout.closeDrawers();
                        in = new Intent(MainActivity.this, ChickenActivity.class);
                        startActivity(in);
                        return true;
                    case R.id.homeTraining:
                        drawerLayout.closeDrawers();
                        in = new Intent(MainActivity.this, HomeTrainingActivity.class);
                        startActivity(in);
                        return true;
                }
                return false;
            }
        });

        userInformation = (TextView) findViewById(R.id.userInfomation);
        userInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userInformation.getText().equals("login")) {
                    Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivityForResult(in, 0);
                }else if(userInformation.getText().equals("logout")){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("로그아웃 하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    userEmail = "unknown";
                                    userInformation.setText("login");
                                }
                            })
                            .setNegativeButton("취소", null).show();
                }
            }
        });

        naviMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.cardView1:
                Intent url1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DQrsQiqphkg"));
                startActivity(url1);
                break;
            case R.id.cardView2:
                Intent url2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=XPlRU-rgcWM"));
                startActivity(url2);
                break;
            case R.id.cardView3:
                Intent url3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=K16eT4Ugt0s"));
                startActivity(url3);
                break;
            case R.id.cardView4:
                Intent url4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9L5uv64498k"));
                startActivity(url4);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            if (resultCode==RESULT_OK) {
                userInformation.setText("logout");
                userEmail = data.getExtras().getString("UserEmail");
                Log.v("email = ", this.userEmail);
            }else{
                Toast.makeText(MainActivity.this, "result cancle!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

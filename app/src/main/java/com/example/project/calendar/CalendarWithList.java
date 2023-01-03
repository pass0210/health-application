package com.example.project.calendar;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.project.calendar.datelist.DateDTO;
import com.example.project.calendar.exerciselist.FragmentActivity;
import com.example.project.R;
import com.example.project.calendar.datelist.mAdapter;
import com.example.project.calendar.request.CalendarRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarWithList extends AppCompatActivity {

    private TextView monthYear;
    private ImageButton previous, next;
    private RecyclerView recyclerView;
    public static mAdapter adapter;
    private ArrayList<DateDTO> dataSet = new ArrayList<>();
    public static LocalDate currentDate;
    private LinearLayoutManager manager;

    public static String userEmail;

    static FragmentManager fm;
    static FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        // 유저 아이디 값 받아오기
        userEmail = bindUserEmail();

        fm = getSupportFragmentManager();
        fragmentRefresh();

        // 변수 값 설정
        monthYear = (TextView) findViewById(R.id.month_year_tv);
        previous = (ImageButton) findViewById(R.id.previous_button);
        next = (ImageButton) findViewById(R.id.next_button);
        recyclerView = (RecyclerView) findViewById(R.id.calendar_list);
        currentDate = LocalDate.now(ZoneId.of("Asia/Seoul"));

        // 리사이클러 뷰 매니저 설정
        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        manager.scrollToPositionWithOffset(currentDate.getDayOfMonth(), 600);
        recyclerView.setLayoutManager(manager);

        // 해당 년월의 일자 리스트 설정
        setListView();

        // 리스너
        // 이전 달로 가기
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDate = currentDate.minusMonths(1);
                setListView();
                recyclerView.scrollToPosition(0);
                adapter = new mAdapter(dataSet, manager);
                recyclerView.setAdapter(adapter);

                fragmentRefresh();
            }
        });

        // 다음 달로 가기
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDate = currentDate.plusMonths(1);
                setListView();
                recyclerView.scrollToPosition(0);
                adapter = new mAdapter(dataSet, manager);
                recyclerView.setAdapter(adapter);

                fragmentRefresh();
            }
        });
    }

    private void setListView() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    LocalDate lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
                    lastDayOfMonth.format(DateTimeFormatter.ofPattern("dd"));

                    dataSet.clear();

                    for (int i = 1; i <= lastDayOfMonth.getDayOfMonth(); i++) {
                        LocalDate date = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), i);
                        DayOfWeek dayOfWeek = date.getDayOfWeek();
                        dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US);

                        dataSet.add(new DateDTO(dayOfWeek.toString().substring(0, 3), Integer.toString(i), 1));
                    }

                    monthYear.setText(currentDate.getMonth() + " " + currentDate.getYear());

                    JSONObject jsonObject = new JSONObject( response );
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        int day = item.getInt("day");
                        dataSet.get(day-1).setComplete(2);
                    }

                    adapter = new mAdapter(dataSet, manager);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                }
            }
        };

        CalendarRequest calendarRequest = new CalendarRequest( userEmail, Integer.toString(CalendarWithList.currentDate.getYear()), Integer.toString(CalendarWithList.currentDate.getMonthValue()), responseListener );
        RequestQueue queue = Volley.newRequestQueue( CalendarWithList.this );
        queue.add( calendarRequest );
    }

    public static void fragmentRefresh() {
        ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, new FragmentActivity());
        ft.commitAllowingStateLoss();
    }

    public static void fragmentRefreshWithData(String dayData) {
        ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, new FragmentActivity(dayData));
        ft.commitAllowingStateLoss();
    }

    public String bindUserEmail() {
        Intent intent = new Intent(this.getIntent());
        String result =  intent.getExtras().getString("UserEmail");
        if (result == null)
            return "unknown";
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("destory = ", "destory");
    }
}
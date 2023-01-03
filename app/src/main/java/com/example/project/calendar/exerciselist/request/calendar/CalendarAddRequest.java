package com.example.project.calendar.exerciselist.request.calendar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CalendarAddRequest extends StringRequest {
    final static private String URL = "http://hoon980923.dothome.co.kr/CalendarAdd.php";
    private Map<String, String> map;

    public CalendarAddRequest(String year, String month, String day, String user_id, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("user_id", user_id);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

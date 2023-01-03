package com.example.project.calendar.exerciselist.request.calendar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SpecificDateRequest extends StringRequest {
    final static private String URL = "http://hoon980923.dothome.co.kr/SpecificDate.php";
    private Map<String, String> map;

    public SpecificDateRequest(String user_id, String year, String month, String day, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

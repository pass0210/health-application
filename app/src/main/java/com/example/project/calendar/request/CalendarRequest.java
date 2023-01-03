package com.example.project.calendar.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CalendarRequest extends StringRequest {

    final static private String URL = "http://hoon980923.dothome.co.kr/Calendar.php";
    private Map<String, String> map;

    public CalendarRequest(String user_id, String year, String month, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("year", year);
        map.put("month", month);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
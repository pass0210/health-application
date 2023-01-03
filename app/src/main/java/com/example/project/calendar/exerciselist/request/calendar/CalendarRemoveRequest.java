package com.example.project.calendar.exerciselist.request.calendar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CalendarRemoveRequest extends StringRequest {
    final static private String URL = "http://hoon980923.dothome.co.kr/CalendarRemove.php";
    private Map<String, String> map;

    public CalendarRemoveRequest(String calendar_id, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("calendar_id", calendar_id);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

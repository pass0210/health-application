package com.example.project.calendar.exerciselist.request.set;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SetListRequest extends StringRequest {
    final static private String URL = "http://hoon980923.dothome.co.kr/SetList.php";
    private Map<String, String> map;

    public SetListRequest(String exercise_id, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("exercise_id", exercise_id);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

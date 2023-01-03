package com.example.project.calendar.exerciselist.request.set;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SetRemoveRequest extends StringRequest {
    final static private String URL = "http://hoon980923.dothome.co.kr/SetRemove.php";
    private Map<String, String> map;

    public SetRemoveRequest(String exercise_id, String set_num, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("exercise_id", exercise_id);
        map.put("set_num", set_num);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

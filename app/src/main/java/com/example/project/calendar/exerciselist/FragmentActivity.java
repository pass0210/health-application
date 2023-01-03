package com.example.project.calendar.exerciselist;

import static com.example.project.calendar.CalendarWithList.currentDate;
import static com.example.project.calendar.CalendarWithList.userEmail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.calendar.CalendarWithList;
import com.example.project.calendar.datelist.mAdapter;
import com.example.project.calendar.exerciselist.request.calendar.CalendarAddRequest;
import com.example.project.calendar.exerciselist.request.calendar.CalendarRemoveRequest;
import com.example.project.calendar.exerciselist.request.calendar.SpecificDateRequest;
import com.example.project.calendar.exerciselist.request.exercise.ExerciseAddRequest;
import com.example.project.calendar.exerciselist.request.exercise.ExerciseListRequest;
import com.example.project.calendar.exerciselist.request.exercise.ExerciseRemoveRequest;
import com.example.project.calendar.exerciselist.request.set.SetAddRequest;
import com.example.project.calendar.exerciselist.request.set.SetCompleteRequest;
import com.example.project.calendar.exerciselist.request.set.SetListRequest;
import com.example.project.calendar.exerciselist.request.set.SetRemoveRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class FragmentActivity extends Fragment {

    // 운동 추가 목록 및 이름 저장 변수
    final String[] words = new String[] {
            "벤치프레스", "스미스머신벤치프레스", "체스트프레스", "렛폴다운", "시티드로우",
            "클로즈그립", "라잉트라이셉스익스텐션", "바벨컬", "케이블푸쉬다운", "스쿼트",
            "레그 익스텐션", "레그컬", "덤벨숄더프레스", "벤트오버레터럴레이즈", "사이드레터럴레이즈"
    };
    private final ArrayList<String> addExerciseList = new ArrayList<>();

    Button buttonAdd;
    LinearLayout containerLayout;
    LayoutInflater layoutInflater;

    String addExerciseTitle = "";
    String dayData = "";
    String calendarId = "";
    String exercise_id = "";

    ProgressDialog dialog;

    class ExerciseAddListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject object = new JSONObject(response);
                exercise_id = object.getString("id");
                addExercise(Integer.parseInt(exercise_id), addExerciseTitle);

            } catch (JSONException e) {
            }
        }
    }

    class CalendarAddListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject object = new JSONObject(response);
                calendarId = object.getString("id");

                ExerciseAddRequest exerciseAddRequest = new ExerciseAddRequest(addExerciseTitle, calendarId, new ExerciseAddListener());
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(exerciseAddRequest);
            } catch (JSONException e) {
            }
        }
    }

    class CalendarRemoveListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            calendarId = "";
        }
    }

    class ExerciseRemovelistener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            if (addExerciseList.size() == 0) {
                CalendarWithList.adapter.setState(Integer.parseInt(dayData)-1, 1);

                CalendarRemoveRequest calendarRemoveRequest = new CalendarRemoveRequest(calendarId, new CalendarRemoveListener());
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(calendarRemoveRequest);
            }
        }
    }

    class SetCompleteListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {}
    }

    class SetAddListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {}
    }

    class SetRemoveListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {}
    }

    public FragmentActivity(){}

    public FragmentActivity(String dayData) {
        this.dayData = dayData;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (!dayData.equals("")) {
            loadExercise();
        }

        View view = inflater.inflate(R.layout.fragment, container, false);

        // 변수 설정
        buttonAdd = view.findViewById(R.id.add);
        containerLayout = view.findViewById(R.id.container);
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 운동 추가 리스너
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setTitle("선택").setSingleChoiceItems(words, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addExerciseTitle = words[which];
                    }
                    // OK 버튼 눌렀을 때 호출되는 리스너
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        if (dayData.equals("")) {
                            Toast.makeText(getContext(), "날짜를 선택해야 운동을 추가할 수 있습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!addExerciseList.contains(addExerciseTitle)) {
                                if (calendarId.equals("")) {
                                    CalendarWithList.adapter.setState(Integer.parseInt(dayData)-1, 2);

                                    CalendarAddRequest calendarAddRequest = new CalendarAddRequest(Integer.toString(currentDate.getYear()), Integer.toString(currentDate.getMonthValue()), dayData, userEmail, new CalendarAddListener());
                                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                    requestQueue.add(calendarAddRequest);
                                }
                                else {
                                    ExerciseAddRequest exerciseAddRequest = new ExerciseAddRequest(addExerciseTitle, calendarId, new ExerciseAddListener());
                                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                    requestQueue.add(exerciseAddRequest);
                                }
                            }
                            else
                                Toast.makeText(getContext(), "이미 존재하는 운동입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("cancel", null).show();
            }
        });

        return view;
    }

    public void loadExercise() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        calendarId = item.getString("id");
                    }

                    if (!calendarId.equals("")){
                        dialog = new ProgressDialog(getContext());
                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.setMessage("운동 불러오는 중...");
                        dialog.setCancelable(false);
                        dialog.show();
                    }

                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
                                JSONArray objectJSONArray = object.getJSONArray("result");

                                for (int i = 0; i < objectJSONArray.length(); i++) {
                                    JSONObject item = objectJSONArray.getJSONObject(i);
                                    String exerciseName = item.getString("exercise_name");
                                    addExerciseTitle = exerciseName;
                                    addExercise(item.getInt("id"), addExerciseTitle);
                                }
                                dialog.dismiss();

                            } catch (JSONException e) {
                            }
                        }
                    };

                    ExerciseListRequest exerciseListRequest = new ExerciseListRequest(calendarId, listener);
                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(exerciseListRequest);

                } catch (JSONException e) {
                }
            }
        };

        SpecificDateRequest specificDateRequest = new SpecificDateRequest(userEmail, Integer.toString(currentDate.getYear()),
                Integer.toString(currentDate.getMonthValue()), dayData, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add( specificDateRequest );
    }

    public void addExercise(int id, String name) {
        int parentId = id;
        String exerciseName = name;

        ArrayList<View> setList = new ArrayList<>();

        final View addView = layoutInflater.inflate(R.layout.row, null);

        // 운동 정보 view에 있는 변수 설정
        Button add = addView.findViewById(R.id.childAdd);
        Button remove = addView.findViewById(R.id.remove);
        ImageView option = addView.findViewById(R.id.option);
        LinearLayout testContainer = addView.findViewById(R.id.testLayout);

        // 운동 이름 설정, 수정 필요
        TextView title = addView.findViewById(R.id.title);
        title.setText(exerciseName);

        // 운동 리스트 추가
        addExerciseList.add(exerciseName);

        // view 추가
        containerLayout.addView(addView);

        // 기존 세트 추가
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray objectJSONArray = object.getJSONArray("result");

                    for (int i = 0; i < objectJSONArray.length(); i++) {
                        JSONObject item = objectJSONArray.getJSONObject(i);

                        final View setView = layoutInflater.inflate(R.layout.set_info, null);

                        TextView setNumText = setView.findViewById(R.id.set_num);
                        TextView weightText = setView.findViewById(R.id.weight);
                        TextView countText = setView.findViewById(R.id.count);
                        CheckBox complete = setView.findViewById(R.id.is_complete);

                        String set_num = item.getString("set_num");

                        setNumText.setText(set_num + " SET");
                        weightText.setText(item.getString("weight") + " Kg");
                        countText.setText(item.getString("count") + " 회");

                        if (item.getInt("set_check") == 1)
                            complete.setChecked(true);

                        complete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int state;

                                if (complete.isChecked())  state = 1;
                                else state = 0;

                                SetCompleteRequest setCompleteRequest = new SetCompleteRequest(Integer.toString(parentId), set_num,
                                        Integer.toString(state), new SetCompleteListener());
                                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                requestQueue.add(setCompleteRequest);
                            }
                        });

                        setList.add(setView);

                        testContainer.addView(setView);
                    }

                } catch (JSONException e) {
                }
            }
        };

        SetListRequest setListRequest = new SetListRequest(Integer.toString(parentId), listener);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(setListRequest);

        // 세트 추가 리스너
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View dialog = layoutInflater.inflate(R.layout.set_dialog, null);

                EditText weightEdit = dialog.findViewById(R.id.weightEdit);
                EditText countEdit = dialog.findViewById(R.id.countEdit);

                // 다이얼로그 띄운 후 세트 정보 입력
                final AlertDialog.Builder setAlert = new AlertDialog.Builder(getActivity());
                setAlert.setTitle("세트 정보 입력")
                        .setView(dialog)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                final View setView = layoutInflater.inflate(R.layout.set_info, null);

                                TextView setNumText = setView.findViewById(R.id.set_num);
                                TextView weightText = setView.findViewById(R.id.weight);

                                String weight = weightEdit.getText().toString();
                                String count = countEdit.getText().toString();

                                if (weight.equals("") || count.equals("")) {
                                    Toast.makeText(getContext(), "Kg과 횟수를 모두 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                TextView countText = setView.findViewById(R.id.count);
                                CheckBox complete = setView.findViewById(R.id.is_complete);

                                String set_num = Integer.toString(setList.size() + 1);

                                setNumText.setText(set_num + " SET");
                                weightText.setText(weight + " Kg");
                                countText.setText(count + " 회");

                                setList.add(setView);
                                testContainer.addView(setView);

                                complete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        int state;

                                        if (complete.isChecked())  state = 1;
                                        else state = 0;

                                        SetCompleteRequest setCompleteRequest = new SetCompleteRequest(Integer.toString(parentId), set_num,
                                                Integer.toString(state), new SetCompleteListener());
                                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                        requestQueue.add(setCompleteRequest);
                                    }
                                });

                                SetAddRequest setAddRequest = new SetAddRequest(Integer.toString(parentId), Integer.toString(setList.size()),
                                        weightEdit.getText().toString(), countEdit.getText().toString(), "0", new SetAddListener());
                                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                requestQueue.add(setAddRequest);
                            }
                        })
                        .setNegativeButton("취소", null).show();
            }
        });

        // 세트 삭제 리스너
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다이얼로그 띄운 후 세트 정보 입력
                final AlertDialog.Builder setAlert = new AlertDialog.Builder(getActivity());
                setAlert.setTitle("세트를 삭제 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int lastIdx = setList.size()-1;
                                if (lastIdx < 0) return;

                                testContainer.removeView(setList.get(lastIdx));
                                setList.remove(lastIdx);

                                SetRemoveRequest setRemoveRequest = new SetRemoveRequest(Integer.toString(parentId),
                                        Integer.toString(lastIdx+1) , new SetRemoveListener());
                                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                requestQueue.add(setRemoveRequest);
                            }
                        })
                        .setNegativeButton("취소", null).show();
            }
        });

        // 운동 삭제 팝업 메뉴 리스너
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        addExerciseList.remove(exerciseName);
                        containerLayout.removeView(addView);

                        ExerciseRemoveRequest exerciseRemoveRequest = new ExerciseRemoveRequest(Integer.toString(parentId), new ExerciseRemovelistener());
                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                        requestQueue.add(exerciseRemoveRequest);

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
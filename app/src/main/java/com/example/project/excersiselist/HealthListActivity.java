package com.example.project.excersiselist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class HealthListActivity extends AppCompatActivity {

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_list_layout);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "가슴"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "벤치프레스", "벤치에 등을 대고 누워서 역기를 가슴 높이까지 내렸다가 굽힌 팔꿈치를 쭉 펴면서 들어 올리는 방식으로 실시한다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "스미스머신벤치프레스", "스미스머신에서 벤치에 등을 대고 누워서 역기를 가슴 높이까지 내렸다가 굽힌 팔꿈치를 쭉 펴면서 들어 올리는 방식으로 실시한다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "체스트프레스", "벤치 프레스 동작을 서서 한다고 생각하면서 천천히 양팔을 이용해 앞으로 뻗으며 가슴(대흉근)을 쥐어 짜듯이 수축시켜 준다. 그리고 이완동작 역시 수축 된 가슴(대흉근)을 늘려준다는 느낌으로 천천히 원위치 해준다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "등"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "렛폴다운", "턱걸이를 하듯 어깨보다 좀 더 넓게 바를 잡은 다음, 상체를 뒤로 젖히고 팔이 아니라 등의 힘으로 당겨 내려오는 방식이 가장 기본적인 형태이다. 턱걸이와 가동부위는 거의 같으며, 좁게 잡을수록 광배근 안쪽이, 넓게 잡을수록 상부와 바깥쪽이 발달된다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "시티드로우", "핸들에서 적당한 거리를 두고 앉은 뒤, 허리가 곧게 편 상태를 계속 유지해 주며 핸들을 몸 쪽으로 당겼다 놓는 동작을 반복한다. 마치 조정선수가 노를 젓는 것처럼 상체를 앞뒤로 젖히며 힘을 더하기도 하지만 상체를 고정시켜서 사용 근육을 특정 부위에 더 고립시켜주기도 한다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "클로즈그립렛풀다운", "기구를 정면으로 보고 앉아 다리를 패드에 고정시키고 클로즈 그립으로 바를 잡는다. 허리를 아치로 고정시키고 그립을 서서히 가스에 닿을때까지 잡아 당기고 숨을 내쉰다. 숨을 들이 마시면서 시작자세로 되돌아 간다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "팔"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "라잉트라이셉스익스텐션", "먼저 벤치에 누운 뒤 바벨을 들어 양팔을 위로 뻗은 상태가 준비 자세이다. 그 다음 천천히 바벨을 봉의 중간점이 이마에 닿을락 말락한 높이까지 내려오게 한 뒤 다시 처음의 자세로 바벨을 들어올린다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "바벨컬", "바벨을 들고 선 상태에서 이두근의 당기는 힘을 이용해 바벨을 들어올린다. 더이상 팔꿈치가 접혀지지 않는 한도까지 이두근에 압박을 준 뒤 천천히 바벨을 내려 준비 자세로 돌아간다. 일반적으로 어깨 넓이보다 살짝 넓게 그립을 잡지만, 더 넓거나 좁게 그립의 너비를 조절하면 각각 이두근의 안쪽이나 바깥쪽에 다른 자극을 줄 수 있다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "케이블푸쉬다운", "케이블 머신에 핸들을 준비 자세로 가져갔을 때보다 좀 더 높게 조정하여 세트 내내 근육이 무게의 힘을 받고 있도록 한다. 기구와 몸 사이의 거리나 상체가 기울어진 정도는 개인의 기호에 따라 다르지만, 팔꿈치가 골반보다 앞에 위치해서 운동 내내 고정된 상태를 유지하는 것만 지켜주면 된다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "하체"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "스쿼트", "하이바 스쿼트 시, 바벨의 상하 운동이 미드풋 점에 수직으로 그려지는 이상적인 운동 궤적에서 종아리가 길 경우 무릎이 발끝보다좀 더 나가는 것은 아주 자연스러운 현상이다. 또한 하이바 스쿼트는 무릎을 굽힌다는 느낌으로 운동이 시작되기 때문에 오히려 무릎 위치를 의식적으로 제어하려고 노력할 경우 전체적인 자세가 무너지며 심각한 부상을 유발할 수 있으므로 주의해야 한다. 무릎의 방향이 발 끝과 일치하느냐가 중요하며, 튀어나가느냐 마느냐는 스쿼트의 종류와 종아리와 허벅지 비율에 따라 변화 할 수 있는 요소이기 때문에 무릎의 전진 또는 후진에 신경을 쓰기 보다는 무릎이 모이거나 벌어지지 않는지, 바벨의 운동 궤적이 버티컬하게 유지되는지, 무게중심이 미드풋에 유지되어 수행중 발 전체가 지면에 제대로 접지되는지, 그리고 수행중 척추가 바르게 정렬되어 있으며 무릎과 고관절에 지나친 회전 토크가 걸리지 않는지를 집중적으로 의식하는 것이 현명하다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "레그 익스텐션", "시트에 앉아 대퇴골의 80%정도가 패드에 닿게 등받이를 조절하고 발목에 바를 위치한 뒤 대퇴사두근을 수축시켜 들어올린다. 이때 대퇴직근에 힘을 주어 들어올려야 한다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "레그컬", "골반 전면부를 패드에 완전히 밀착시키고 무릎의 방향과 발끝의 위치까지 정확히 맞추려 노력합니다. 동작을 시작하기 앞서서 반드시 무릎을 살짝 구부린 상태로 허벅지 뒷쪽에 무게를 실어 놓고 패드를 들어 올린다는 생각이 아닌 근육의 짧아졌다가 늘어난다는 느낌으로 동작합니다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "어깨"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "덤벨숄더프레스", "팔을 옆으로 벌려서 덤벨을 머리와 나란히 뒀어요. 위치는 머리의 중간 - 귀가 있는 지점이 제일 적당합니다. 어깨가 솟지 않게 최대한 눌러주고 움직이지 않게 제대로 유지한 상태에서 팔만 들어줍니다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "벤트오버레터럴레이즈", "벤트오버 레터럴 레이즈 자세는 어깨의 뒷부분 후면을 자극하기에 가장 좋은 자세다. 팔을 옆으로 뻗는 동작은 동일하나, 벤트오버는 몸통을 숙이고 하는 운동이다."));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "사이드레터럴레이즈", "사이드 레터럴 레이즈는 정면을 보고 팔을 옆으로 뻗는운동이다."));

        recyclerview.setAdapter(new ExpandableListAdapter(data));
    }
}

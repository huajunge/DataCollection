package com.example.tr.datacollection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccidentInfo extends Fragment {
    private Spinner spxingqi;
    private Spinner spyanzhongcd;
    private Spinner spfeipengzhuang;
    private Spinner spgudingwu;
    private Spinner spfeigudingwu;
    private Spinner spgongjiao;
    private Spinner spzhaoshity;
    private Spinner spweixian;

    private EditText driverNums;
    private EditText ssrenshu;
    private EditText dieNums;
    private EditText carnum;


    public AccidentInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accident_info, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        spxingqi=(Spinner)view.findViewById(R.id.sp_xingqi);
        String[] strspxingqi    = {"一","二","三","四","五","六","七"};
        spxingqi.setAdapter(new MyAdapter(strspxingqi,view.getContext()).getAdaper());
        
        spyanzhongcd=(Spinner)view.findViewById(R.id.sp_shiguchengdu);
        String[] strspyanzhongcd    = {"致命伤（K）","疑似严重受伤（A）","疑似轻微受伤（B）","可能受伤（C）","仅财产损失（O）","未知"};
        spyanzhongcd.setAdapter(new MyAdapter(strspyanzhongcd,view.getContext()).getAdaper());
        
        //
        spfeipengzhuang=(Spinner)view.findViewById(R.id.sp_feipengz);
        String[] strspfeipengzhuang    = {"非碰撞","翻车","火灾/爆炸","淹没，全部或部分","弯折现象","货物/设备丢失/转移","设备损坏（爆胎，制动失效等）",
                "单元分离","右侧驶离道路","左侧驶离道路","穿越隔离带","穿越道路中心线","下坡失控","落/跳车","重返行车道","丢弃或遗落物品","其他非碰撞事件"};
        spfeipengzhuang.setAdapter(new MyAdapter(strspfeipengzhuang,view.getContext()).getAdaper());
        
        spgudingwu=(Spinner)view.findViewById(R.id.sp_zhuangwu);
        String[] strspgudingwu   = {"碰撞衰减器/防撞墩","桥架结构","桥墩或桥梁支撑结构","桥梁轨道","缆索护栏","涵洞","路缘","沟渠","路堤",
                "护栏表面","护栏尾部","混凝土交通障碍物","其他交通障碍物","树木（直立的）","电线杆/电灯支撑结构","交通标志支撑结构",
                "交通信号灯支撑结构","护栏","邮箱","其他邮件、灯具支撑结构","其他固定物（墙体、建筑物、隧道等）","未知"};
        spgudingwu.setAdapter(new MyAdapter(strspgudingwu,view.getContext()).getAdaper());
        
        
        spfeigudingwu=(Spinner)view.findViewById(R.id.sp_zhuangren);
        String[] strspfeigudingwu     = {"行人","自行车","其他非机动车","轨道车辆（火车、动力车）","动物（活物）","交通机动车辆","停放机动车辆",
                "落物打击，由机动车造成的货物运输或其他任何物体的移动","施工区/设备维护","其他非固定物"};
        spfeigudingwu.setAdapter(new MyAdapter(strspfeigudingwu,view.getContext()).getAdaper());
        
        spgongjiao=(Spinner)view.findViewById(R.id.sp_busshuxing);
        String[] strspgongjiao    = {"非巴士","学校用途","公交/通勤","城际","租赁/旅游","穿梭巴士"};
        spgongjiao .setAdapter(new MyAdapter(strspgongjiao,view.getContext()).getAdaper());
        
        
        spzhaoshity=(Spinner)view.findViewById(R.id.sp_run);
        String[] strspzhaoshity    = {"否，没有离开现场","是，驾驶员或者车辆与驾驶员离开现场"};
        spzhaoshity.setAdapter(new MyAdapter(strspzhaoshity,view.getContext()).getAdaper());

        spweixian= (Spinner) view.findViewById(R.id.sp_dangers);
        String[] strspweixian    = {"是","否"};
        spweixian .setAdapter(new MyAdapter(strspweixian,view.getContext()).getAdaper());

        driverNums= (EditText) view.findViewById(R.id.renshu);
        ssrenshu= (EditText) view.findViewById(R.id.jidongcheshu);
        dieNums= (EditText) view.findViewById(R.id.ssrenshu);
        carnum= (EditText) view.findViewById(R.id.swrenshu);
    }

}

package com.example.tr.datacollection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmInfo extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spAcctype;
    private Spinner spCountry;
    private Spinner spCity;
    private Spinner spShigu;
    private Spinner spShiguType;
    private Spinner spCaraccident;
    private Spinner spTqcondition;
    private Spinner spLightcondition;
    private Spinner spRoutecondition;
    private Spinner spRouteposition;
    private Spinner spJck;
    private Spinner spJcktype;
    private Spinner spTsposition;
    private Spinner spResultem;

    private Spinner spResultroute;
    private Spinner spWorktype;
    private Spinner spHavepeople;
    private Spinner spXczhifa;
    private Spinner spOnbulid;

    private TextView day;
    private TextView time;
    private TextView lnglat;
    private Button btnMap;

    String[] strspShiguType_feipz={"翻车","火灾/爆炸","淹没，全部或部分","弯折现象","货物/设备损失或转移","从车辆掉落/跳下	物体丢掷或掉落","其他非碰撞事故"};
    String[] strspShiguType_feigudingc={"行人","自行车","其他非机动车","轨道车辆（火车、动力车","动物（活物）","交通机动车辆","停放机动车辆",
            "坠落物打击，由机动车造成货物运输或其他任何物体的移动","施工区/设备维护","其他非固定物	"};
    String[] strspShiguType_guding={"碰撞衰减器/防撞墩	桥架结构","桥墩或桥梁支撑结构","桥梁轨道","缆索护栏","涵洞","路缘","沟渠","路堤","护栏表面",
            "护栏尾部	混凝土交通障碍物","其他交通障碍物","树木（直立的）","电线杆/电灯支撑结构","交通标志支撑结构","交通信号灯支撑结构	护栏","邮箱	其他邮件、灯具支撑结构	其他固定物（墙体、建筑物、隧道等）","未知	"};

    public EmInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_em_info, container, false);
        intiView(view);
        return view;
    }

    private void intiView(View view) {
        spAcctype = (Spinner) view.findViewById(R.id.sp_acctype);
        String[] strspAcctype={" ","公共性质","私人性质","公共行车道，道路中","公共行车道，非道路中","非公共行车道"};
        spAcctype.setAdapter(new MyAdapter(strspAcctype,view.getContext()).getAdaper());
//车间事故
        spCaraccident =(Spinner) view.findViewById(R.id.sp_caraccident);
        String[] strspCaraccident={	"","追尾碰撞","正面碰撞","侧面碰撞（同向）","侧面碰撞（对向）","侧面","碰撞（直角）","侧面碰撞（角度不确定）",
                "同向刮擦","对向刮擦","其他角度碰撞"};
        spCaraccident.setAdapter(new MyAdapter(strspCaraccident,view.getContext()).getAdaper());
//事故城市
        spCity =(Spinner) view.findViewById(R.id.sp_city);
        String[] strspCity={"成都","绵阳市","自贡市","攀枝花市","泸州市","德阳市","广元市","遂宁市","内江市","乐山市","资阳市","宜宾市","南充市","达州市",
                "雅安市","	阿坝藏族羌族自治州","甘孜藏族自治州","凉山彝族自治州	广安市","巴中市","眉山市"};
        spCity.setAdapter(new MyAdapter(strspCity,view.getContext()).getAdaper());
//事故县区
        spCountry = (Spinner) view.findViewById(R.id.sp_country);
        String[] strspCountry={"郫县","新都","金牛区，金牛区","金牛区","金牛区"};
        spCountry.setAdapter(new MyAdapter(strspCountry,view.getContext()).getAdaper());

//是否有工人
        String[] strHavepeopel={"否","是","未知"};
        spHavepeople =(Spinner) view.findViewById(R.id.sp_havepeople);
        spHavepeople.setAdapter(new MyAdapter(strHavepeopel,view.getContext()).getAdaper());
//立交区域
        spJck = (Spinner) view.findViewById(R.id.sp_jck);
        String[] strspJck={"否","是","未知"};
        spJck.setAdapter(new MyAdapter(strspJck,view.getContext()).getAdaper());

//交叉口类型
        spJcktype= (Spinner) view.findViewById(R.id.sp_jcktype);
        String[] strspJcktype={"非交叉口","十字交叉口","T形交叉口","Y形交叉口","L形交叉口","环形交叉口","环岛","五或更多条腿交叉口"};
        spJcktype.setAdapter(new MyAdapter(strspJcktype,view.getContext()).getAdaper());


//光照情况
        spLightcondition  =(Spinner)  view.findViewById(R.id.sp_lightcondition);
        String[] strspLightcondition={"白天","凌晨","夜晚","黄昏","夜间无路灯照明","夜间光线状况不明","其他	未知"};
        spLightcondition.setAdapter(new MyAdapter(strspLightcondition,view.getContext()).getAdaper());
//是否发生在建设区
        spOnbulid = (Spinner) (Spinner) view.findViewById(R.id.sp_onbulid);
        String[] strspOnbulid={"否","是","未知"};
        spOnbulid.setAdapter(new MyAdapter(strspOnbulid,view.getContext()).getAdaper());
//致因条件，环境
        spResultem = (Spinner) view.findViewById(R.id.sp_resultem);
        String[] strspResultem={	"无	","天气状况","视觉障碍","炫目","道路中出现动物","其他","未知"};
        spResultem.setAdapter(new MyAdapter(strspResultem,view.getContext()).getAdaper());

//致因条件，道路
        spResultroute = (Spinner) view.findViewById(R.id.sp_resultroute);
        String[] strspResultroute={"无","由于之前的事故而滞留","由于之前的非重复性事件而滞留	","由于常规拥堵而滞留","与收费站或购物有关","道路表面情况（湿、滑、积雪、泥泞等）","废弃物","沟槽、孔洞、突起物	作业区（修建/养护/公共设施维护）"
                ,"道路阻挡物","交通控制装置无效、遗失、模糊	路肩（无、太低、太软、太高）","无道路区域	其他","未知"};
        spResultroute.setAdapter(new MyAdapter(strspResultroute,view.getContext()).getAdaper());

//道路状况
        spRoutecondition =(Spinner)  view.findViewById(R.id.sp_routecondition);
        String[] strspRoutecondition={	"干燥","潮湿","积雪","泥泞","	积冰/霜	积水（固定，流动）","砂石	泥、灰尘、沙砾	油","其他","未知"};
        spRoutecondition.setAdapter(new MyAdapter(strspRoutecondition,view.getContext()).getAdaper());
//车道位置
        spRouteposition = (Spinner) view.findViewById(R.id.sp_chedaoposition);
        String[] strspRouteposition={"在路中","在路肩","路中心线","在路旁","三角区","隔离带	","停车道路或区域","非道路中，未知位置","道路（公共行车道）","未知"};
        spRouteposition.setAdapter(new MyAdapter(strspRouteposition,view.getContext()).getAdaper());

//危险事件
        spShigu = (Spinner) view.findViewById(R.id.shijian);
        String[] strspShigu={"非碰撞","撞人、撞车或其他非固定物","碰撞固定物"};
        spShigu.setAdapter(new MyAdapter(strspShigu,view.getContext()).getAdaper());

//危险事件类型
        spShiguType = (Spinner) view.findViewById(R.id.sp_shijian_type);

        spShiguType.setAdapter(new MyAdapter(strspShiguType_feipz,view.getContext()).getAdaper());

//天气状况
        spTqcondition = (Spinner) view.findViewById(R.id.sp_tqcondition);
        String[] strspTqcondition={"晴朗","多云","雾，烟，烟雾","雨","雨夹雪或冰雹","冻雨或冻雾雨","雪","飘雪","严重侧风","沙尘","其他","未知"};
        spTqcondition.setAdapter(new MyAdapter(strspTqcondition,view.getContext()).getAdaper());

//特殊位置
        spTsposition = (Spinner) view.findViewById(R.id.sp_tsposition);
        String[] strspTsposition={"非交叉口","十字交叉口 ","与十字交叉口相关","进/出口匝道","与进/出口匝道相关","铁路平交道","与转线道相关","接近连接道路","与连接道路相关","公用路径或小道","加速/减速车道","穿越道路","其他以上未列出但与交叉口相关区域","未知"};
        spTsposition.setAdapter(new MyAdapter(strspTsposition,view.getContext()).getAdaper());

//作业区类型        
        spWorktype =(Spinner)  view.findViewById(R.id.sp_worktype);
        String[] strspWorktype={"车道关闭","车道变换","工作于路肩或中央带","间歇或移动作业","其他"};
        spWorktype.setAdapter(new MyAdapter(strspWorktype,view.getContext()).getAdaper());
        
// 现场执法  
        spXczhifa = (Spinner) view.findViewById(R.id.sp_xczhifa);
        String[] strspXczhifa={"无","仅有执法人员","仅有执法车辆"};
        spXczhifa.setAdapter(new MyAdapter(strspXczhifa,view.getContext()).getAdaper());

        Date date = new Date(System.currentTimeMillis());
        day = (TextView) view.findViewById(R.id.text_day);
      // Format format =new Format("yyyy-mm-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        day.setText(simpleDateFormat.format(date).toString());
        time = (TextView) view.findViewById(R.id.text_time);
        lnglat = (TextView) view.findViewById(R.id.text_jinweidu);
        btnMap = (Button) view.findViewById(R.id.btn_map);

        initClick(view);
    }

    private void initClick(View view) {
       // spAcctype.set
         spAcctype.setOnItemSelectedListener(this);
         spCountry.setOnItemSelectedListener(this);
         spCity.setOnItemSelectedListener(this);
         spShigu.setOnItemSelectedListener(this);
         spShiguType.setOnItemSelectedListener(this);
         spCaraccident.setOnItemSelectedListener(this);
         spTqcondition.setOnItemSelectedListener(this);
         spLightcondition.setOnItemSelectedListener(this);
         spRoutecondition.setOnItemSelectedListener(this);
         spRouteposition.setOnItemSelectedListener(this);
         spJck.setOnItemSelectedListener(this);
         spJcktype.setOnItemSelectedListener(this);
         spTsposition.setOnItemSelectedListener(this);
         spResultem.setOnItemSelectedListener(this);

         spResultroute.setOnItemSelectedListener(this);
         spWorktype.setOnItemSelectedListener(this);
         spHavepeople.setOnItemSelectedListener(this);
         spXczhifa.setOnItemSelectedListener(this);
         spOnbulid.setOnItemSelectedListener(this);

         day.setOnClickListener(this);
         time.setOnClickListener(this);
        // lnglat;
         btnMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Log.i("shijians","in");
//        Log.i("shijians","in"+i);
//        Log.i("shijians","viewid:"+view.getId());
//        Log.i("shijians","adapterViewid:"+adapterView.getId());
//        Log.i("shijians","R.id.shijian"+R.id.shijian);
        switch (adapterView.getId()){
            case R.id.shijian:
                if(spShiguType!=null){
                    try{
                    if(i==0){
                        spShiguType.setAdapter(new MyAdapter(strspShiguType_feipz,view.getContext()).getAdaper());
                    }else if(i ==1){
                        spShiguType.setAdapter(new MyAdapter(strspShiguType_feigudingc,view.getContext()).getAdaper());
                    }else{
                        spShiguType.setAdapter(new MyAdapter(strspShiguType_guding,view.getContext()).getAdaper());
                    }}catch (Exception e){
                        //spShiguType = (Spinner) view.findViewById(R.id.sp_shijian_type);
                    }
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

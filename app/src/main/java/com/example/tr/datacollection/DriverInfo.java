package com.example.tr.datacollection;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tr.datacollection.model.CarData;
import com.example.tr.datacollection.model.PelpelData;
import com.example.tr.datacollection.model.PeopelData2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;


/**
 * A simple {@link Fragment} subclass.
 */
public class DriverInfo extends Fragment implements View.OnClickListener {

    private ListView list_info;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>>datalist;
    private LinearLayout layoutPeoPel1;
    private LinearLayout layoutPeoPel2;
    private LinearLayout layoutPeoPel3;

    private LinearLayout layjiashiren;
    private LinearLayout laychengzuoren;
    private LinearLayout layqitaren;
    public DriverInfo() {
        // Required empty public constructor
    }
    private ImageView JiaShiren;
    private ImageView ChengZhuoRen;
    private ImageView QiTaRen;

    //其他人
    private Spinner SPsgfssxingwei;
    private Spinner SPsgfsqxingwei;

    private Spinner SPaqsbsyzhuangtai;
    private Spinner SPsgfssposition;


    private Spinner xunhao;
    //驾驶人信息
    private EditText SPname;
    private Spinner SPxingbie;
    private Spinner SPshenfentype;
    private Spinner SPrenyuantype;
    private Spinner SPsschengdu;
    private EditText phonenum;
    private Spinner SPcheliangxunhao;
    private Spinner SPysxitong;
    private Spinner SPtuokuishiyong;
    private Spinner SPaqqnzt;
    private Spinner SPpaochuzhuangta;
    private Spinner SPsgfsszhuangtai;
    private Spinner SPyinjiu;
    private Spinner SPduPingLeiXing;
    private Spinner SPceshizhuangtai;
    private Spinner SPceshitype;
    private Spinner SPceshiresult;
    private EditText jiazhao;
    private Spinner dengji;
    private Spinner shangyejiazhao;
    private Spinner qianzhu;
    private Spinner guanxiaqu;
    private Spinner chaosuxunwen;
    private Spinner fenxinjiashi;
    private Spinner sgfssxingwie;
    private Spinner sgfsszhuangt;
    private Spinner jtwfxingwei;
    private Spinner jiazhaoxianzhi;
    //酒精测试
    private Spinner SPJJceshizhuangtai;
    private Spinner SPJJceshitype;
    private Spinner SPJJceshiresult;
    private TextView chuShenRi;
    private Calendar cal;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    
    private Button btnXingWei;
    private Button btnZhuangTai;
    private Button btnWeiFa;
    private Button btnxianzhi;
    private Button btnshibei;
    private int xingweinums=0;
    private int zhuangtainums=0;
    private int weifanum=0;
    private int xianzhinum=0;
    private int shibeinum=0;

    //乘坐人信息
    private Spinner paiWei;
    private Spinner weiZhi;
    private Spinner qiTaWeiZhi;

    //保存
    private Button SAVE;
    private TextView peopelAD;//已添加人数
    private int peopelAdded =0;

    private ProgressDialog processDialog;//保存提醒框

    //输入框
    private EditText name;
 //   private

    private String str_xingWei   = "";  //行为
    private String str_zhuangTai = "";//状态
    private String str_weifa = "";    //违法

    private String str_zhuangTai2 = "";//状态

    private String str_xingWei3 = "";  //行为
    private String str_zhuangtai3 = ""; //状态
    private String str_shebei = "";      //设备


    private List<PeopelData3> peopel3 = new ArrayList<>();
    private List<PeopelData2> peopel2 = new ArrayList<>();
    private List<PelpelData>  peopel1 = new ArrayList<>();
    private int TYPE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_info, container, false);
        init(view);
        initJiaShiRen(view);
      //  initChengZhuorne(view);
      //  initQitaRen(view);
        return view;
    }

    private void initQitaRen(View view) {
        SPname = (EditText)layoutPeoPel3.findViewById(R.id.sp_name);
//获取时间对象
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH)+1;
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour=cal.get(Calendar.HOUR_OF_DAY);
        minute=cal.get(Calendar.MINUTE);
        chuShenRi = (TextView) layoutPeoPel3.findViewById(R.id.chushenri);
        chuShenRi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        chuShenRi.setText(i+"年"+(i1+1)+"月"+i2+"日");
                    }
                },year,cal.get(Calendar.MONTH),day).show();
            }
        });

//性别
        SPxingbie = (Spinner)layoutPeoPel3.findViewById(R.id.sp_xingbie);
        String[] strSPxingbie ={"男","女"};
        SPxingbie.setAdapter(new MyAdapter2(strSPxingbie,getContext()).getAdaper());
//身份
        SPshenfentype = (Spinner)layoutPeoPel3.findViewById(R.id.sp_shenfentype);
        String[] strSPshenfentype ={"公务员","公安民警","职员","工人","农民","自主经营者","军人","武警","教师","大（专）学生","中（专）学生","小学生","学前儿童","港澳同胞","华侨","外国人","外来务工者","不在业人员","其他"};
        SPshenfentype.setAdapter(new MyAdapter(strSPshenfentype,getContext()).getAdaper());
//关系
        SPrenyuantype = (Spinner)layoutPeoPel3.findViewById(R.id.sp_renyuantype);
        String[] strSPrenyuantype ={"无","夫妻","父子/父女","亲人","朋友"};
        SPrenyuantype.setAdapter(new MyAdapter(strSPrenyuantype,getContext()).getAdaper());
//受伤程度
        SPsschengdu = (Spinner)layoutPeoPel3.findViewById(R.id.sp_sszhuangtai);
        String[] strSPsszhuangtai ={"致命伤","重伤","疑似轻微受伤","可能受伤","无明显受伤"};
        SPsschengdu.setAdapter(new MyAdapter(strSPsszhuangtai,getContext()).getAdaper());

        phonenum = (EditText) layoutPeoPel3.findViewById(R.id.phonenum);
//车辆序号
        SPcheliangxunhao = (Spinner)layoutPeoPel3.findViewById(R.id.zsjdcxunhao);
        String[] strSPcheliangxunhao ={"暂无"};

//        List<CarData> carDatas = ((MainActivity)getActivity()).getCarData();
//        String[] strXunhao = new String[carDatas.size()];
//        int i=0;
//        Log.i("data","data----------------");
//        for(CarData c :carDatas){
//            strXunhao[i++]=c.getXunhao()+" "+c.getChepaihao();
//            Log.i("data",strXunhao[i-1]);
//        }
//        //SPcheliangxunhao.setAdapter(new MyAdapter(strXunhao,getContext()).getAdaper());
       //SPcheliangxunhao.setAdapter(new MyAdapter(strSPcheliangxunhao,getContext()).getAdaper());
       // xunhao = (Spinner) layoutPeoPel3.findViewById(R.id.zsjdcxunhao);
        List<CarData> carDatas2 = ((MainActivity)getActivity()).getCarData();
        String[] strXunhao2 = new String[carDatas2.size()];
        int i2=0;
        Log.i("data","data----------------");
        for(CarData c :carDatas2){
            strXunhao2[i2++]=c.getXunhao()+" "+c.getChepaihao();
            Log.i("data",strXunhao2[i2-1]);
        }
        SPcheliangxunhao.setAdapter(new MyAdapter2(strXunhao2,getContext()).getAdaper());
//事故发生时状态
        SPsgfsszhuangtai = (Spinner)layoutPeoPel3.findViewById(R.id.sp_sgfsszhuangtai);
        String[] strSPsgfsszhuangtai ={"明显正常","身体受损","情绪化（抑郁、生气、不安等）","生病（不舒服）、昏厥",
                "睡着的或疲乏的","受药物/毒品/酒精影响","其他","未知"};
        SPsgfsszhuangtai.setAdapter(new MyAdapter(strSPsgfsszhuangtai,getContext()).getAdaper());
//怀疑饮酒
        SPyinjiu = (Spinner)layoutPeoPel3.findViewById(R.id.sp_yinjiu);
        String[] strSPyinjiu ={"是","否","未知"};
        SPyinjiu.setAdapter(new MyAdapter2(strSPyinjiu,getContext()).getAdaper());

//事故发生是状态
        SPsgfssxingwei = (Spinner) layoutPeoPel3.findViewById(R.id.sp_sgfssxingwei);
        String[] strSPsgfssxingwei = {"无","穿越道路","等待穿越道路","顺着交通流方向沿着道路步行/骑车（在或邻着行车道）","逆着交通流方向沿着道路步行/骑车（在或邻着行车道）","在人行道上步行/骑车","在车行道上—其他","邻着车行道（例如，路肩，路中）","在道路中作业（应急响应）","其他","无","未知"};
        SPsgfssxingwei.setAdapter(new MyAdapter(strSPsgfssxingwei,getContext()).getAdaper());

        SPsgfsqxingwei = (Spinner) layoutPeoPel3.findViewById(R.id.sp_sgfsqxingwei);
        String[] strSPsgfsqxingwei = {"不合适行为","飞奔/猛冲","违法占道","违反交通标志、信号灯、交警","在行车道不合适行为（站立、躺、工作、玩耍）","处理残缺车辆（工作、推、离开/到达）","进/出停止的车辆/站立在车辆上","疏忽（说话、进食等）","不明显可见（黑衣服、无照明等）","不合适的转向/合流","不合适的通行","逆向骑行或步行","其他","未知"};
        SPsgfsqxingwei.setAdapter(new MyAdapter(strSPsgfsqxingwei,getContext()).getAdaper());

        SPaqsbsyzhuangtai = (Spinner) layoutPeoPel3.findViewById(R.id.sp_aqsbsyzhuangtai);
        String[] strSPaqsbsyzhuangtai = {"无","头盔","使用保护垫（肘、膝盖、小腿等）","反光衣物（夹克、双肩包等）","照明","其他","无可适用"};
        SPaqsbsyzhuangtai .setAdapter(new MyAdapter(strSPaqsbsyzhuangtai,getContext()).getAdaper());

        SPsgfssposition = (Spinner) layoutPeoPel3.findViewById(R.id.sgfssposition);
        String[] strSPsgfssposition = {"十字路口—有标志的人行横道","十字路口—无标志的人行横道","十字路口—其他","路中—有标志的人行横道","车行道—其他位置","自行车道","路肩/路边","人行道","中央分隔带/安全岛","进入车道","公用路径或小道 ","无行车道区域","其他","未知"};
        SPsgfssposition.setAdapter(new MyAdapter(strSPsgfssposition,getContext()).getAdaper());



//毒品类型
        SPduPingLeiXing = (Spinner)layoutPeoPel3.findViewById(R.id.sp_dupinleixing);
        String[] strSPduPingLeiXing ={"无","大麻","可卡因","鸦片","苯丙胺","五氯苯酚","其他控制的物质","其他毒品（不包括事故后的毒品）"};
        SPduPingLeiXing.setAdapter(new MyAdapter(strSPduPingLeiXing,getContext()).getAdaper());
//测试状态
        SPceshizhuangtai = (Spinner)layoutPeoPel3.findViewById(R.id.sp_ceshizhuangtai);
        String[] strSPceshizhuangtai ={"未做测试","拒绝测试","做了测试","未知是否测试"};
        SPceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,getContext()).getAdaper());

        SPceshitype = (Spinner)layoutPeoPel3.findViewById(R.id.sp_ceshitype);
        String[] strSPceshitype ={"无","血液","呼吸","尿液","其他"};
        SPceshitype .setAdapter(new MyAdapter(strSPceshitype,getContext()).getAdaper());
        SPceshiresult = (Spinner) (Spinner)layoutPeoPel3.findViewById(R.id.sp_ceshiresult);
        String[] strSPceshiresult ={"无","阳性","阴性","未知"};
        SPceshiresult .setAdapter(new MyAdapter2(strSPceshiresult,getContext()).getAdaper());
        //酒精测试
        SPJJceshizhuangtai = (Spinner)layoutPeoPel3.findViewById(R.id.jjceshizhuangtai);
        SPJJceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,getContext()).getAdaper());

        SPJJceshitype = (Spinner)layoutPeoPel3.findViewById(R.id.jjceshitype);
        SPJJceshitype .setAdapter(new MyAdapter(strSPceshitype,getContext()).getAdaper());

        SPJJceshiresult = (Spinner) (Spinner)layoutPeoPel3.findViewById(R.id.jjceshiresult);
        String[] strSPceshiresult2 ={"无","血液中酒精浓度测试结果","确认是否含酒精","待定","未知"};
        SPJJceshiresult .setAdapter(new MyAdapter(strSPceshiresult2,getContext()).getAdaper());
        //按钮事件

        btnZhuangTai = (Button) layoutPeoPel3.findViewById(R.id.btn_addzhuangtai);
        btnZhuangTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) layoutPeoPel3.findViewById(R.id.text_zhuangtainums);
                textView.setText("已添加状态数"+(++zhuangtainums));
                str_zhuangtai3 += SPsgfsszhuangtai.getSelectedItem().toString()+" ; ";
            }
        });
        btnXingWei = (Button) layoutPeoPel3.findViewById(R.id.btn_xingshixingwei);
        btnXingWei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) layoutPeoPel3.findViewById(R.id.xingweishu);
                textView.setText("已添加行为数"+(++xingweinums));
                str_xingWei3 += SPsgfsqxingwei.getSelectedItem().toString();
            }
        });
        btnshibei = (Button) layoutPeoPel3.findViewById(R.id.btn_addshibei);
        btnshibei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) layoutPeoPel3.findViewById(R.id.text_shibeinum);
                textView.setText("已添加设备数"+(++shibeinum));
                str_shebei = SPaqsbsyzhuangtai.getSelectedItem().toString();
            }
        });

    }

    private void initChengZhuorne(View view) {

        //获取时间对象
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH)+1;
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour=cal.get(Calendar.HOUR_OF_DAY);
        minute=cal.get(Calendar.MINUTE);
        chuShenRi = (TextView) layoutPeoPel2.findViewById(R.id.chushenri);
        chuShenRi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        chuShenRi.setText(i+"年"+(i1+1)+"月"+i2+"日");
                    }
                },year,cal.get(Calendar.MONTH),day).show();
            }
        });

        xunhao = (Spinner) layoutPeoPel2.findViewById(R.id.sp_cheliangxunhao2);
        List<CarData> carDatas = ((MainActivity)getActivity()).getCarData();
        String[] strXunhao = new String[carDatas.size()];
        int i=0;
        Log.i("data","data----------------");
        for(CarData c :carDatas){
            strXunhao[i++]=c.getXunhao()+" "+c.getChepaihao();
            Log.i("data",strXunhao[i-1]);
        }
        xunhao.setAdapter(new MyAdapter(strXunhao,getContext()).getAdaper());

//        Spinner  SPxunhao = (Spinner)layoutPeoPel2.findViewById(R.id.sp_cheliangxunhao);
//        String[] strSPxunhao ={"男","女"};
//        SPxunhao.setAdapter(new MyAdapter(strSPxunhao,getContext()).getAdaper());
//性别
        SPxingbie = (Spinner)layoutPeoPel2.findViewById(R.id.sp_xingbie);
        String[] strSPxingbie ={"男","女"};
        SPxingbie.setAdapter(new MyAdapter2(strSPxingbie,getContext()).getAdaper());
//身份
        SPshenfentype = (Spinner)layoutPeoPel2.findViewById(R.id.sp_shenfentype);
        String[] strSPshenfentype ={"公务员","公安民警","职员","工人","农民","自主经营者","军人","武警","教师","大（专）学生","中（专）学生","小学生","学前儿童","港澳同胞","华侨","外国人","外来务工者","不在业人员","其他"};
        SPshenfentype.setAdapter(new MyAdapter(strSPshenfentype,getContext()).getAdaper());
//关系
        SPrenyuantype = (Spinner)layoutPeoPel2.findViewById(R.id.sp_renyuantype);
        String[] strSPrenyuantype ={"夫妻","父子/父女","亲人","朋友"};
        SPrenyuantype.setAdapter(new MyAdapter(strSPrenyuantype,getContext()).getAdaper());
//受伤程度
        SPsschengdu = (Spinner)layoutPeoPel2.findViewById(R.id.sp_sszhuangtai);
        String[] strSPsszhuangtai ={"致命伤","重伤","疑似轻微受伤","可能受伤","无明显受伤"};
        SPsschengdu.setAdapter(new MyAdapter(strSPsszhuangtai,getContext()).getAdaper());

        SPname = (EditText)layoutPeoPel2.findViewById(R.id.sp_name);
        phonenum = (EditText) layoutPeoPel2.findViewById(R.id.phonenum);
//车辆序号
//        SPcheliangxunhao = (Spinner)layoutPeoPel2.findViewById(R.id.sp_cheliangxunhao2);
//        String[] strSPcheliangxunhao ={""};
//        SPcheliangxunhao.setAdapter(new MyAdapter(strSPcheliangxunhao,getContext()).getAdaper());
////座位位置
//         SPposition = (Spinner)layoutPeoPel2.findViewById(R.id.sp_position);
//        String[] str ={""};
//        .setAdapter(new MyAdapter(str,getContext()).getAdaper());
//座位位置
        //排位
        paiWei = (Spinner) layoutPeoPel2.findViewById(R.id.sp_pai);
        String[] strpaiWei = {"前排","第二排","第三排","第四排","其他排（公交车、15座厢式货车等。）"};
        paiWei.setAdapter(new MyAdapter(strpaiWei,getContext()).getAdaper());

        //座位
        weiZhi = (Spinner) layoutPeoPel2.findViewById(R.id.sp_zuowei);
        String[] strweiZhi = {"右侧","左侧（通常是汽车或摩托车驾驶员，除了邮政车辆和一些外国车辆）","中部","其他","未知"};
        weiZhi.setAdapter(new MyAdapter(strweiZhi,getContext()).getAdaper());

        //其他
        qiTaWeiZhi = (Spinner) layoutPeoPel2.findViewById(R.id.sp_qitaweizhi);
        String[] strqiTaWeiZhi = {"不可用的","驾驶室（卡车）的卧铺","其他封闭的载货区域","未封闭载货区域","牵引单元","汽车外部的（非牵引单元）","未知"};
        qiTaWeiZhi.setAdapter(new MyAdapter(strqiTaWeiZhi,getContext()).getAdaper());


//安全约束
        SPysxitong = (Spinner)layoutPeoPel2.findViewById(R.id.sp_ysxitong);
        String[] strSPysxitong ={"无可用",
                "未被使用—机动车使用者","使用肩带和腰带","仅使用肩带","仅使用腰带","使用约束—类型未知",
                "儿童约束系统—前向","儿童约束系统—后向","儿童垫高座椅","儿童约束—类型未知","其他","未知"};
        SPysxitong.setAdapter(new MyAdapter(strSPysxitong,getContext()).getAdaper());
        //头盔使用
        SPtuokuishiyong = (Spinner)layoutPeoPel2.findViewById(R.id.sp_tuokuishiyong);
        String[] strSPtuokuishiyong ={"符合DOT规格的摩托车头盔",
                "除符合DOT规格的其他头盔","未知是否符合DOT规格的头盔","无头盔","未知是否戴头盔"};
        SPtuokuishiyong.setAdapter(new MyAdapter(strSPtuokuishiyong,getContext()).getAdaper());
//安全气囊
        SPaqqnzt = (Spinner)layoutPeoPel2.findViewById(R.id.sp_aqqnzt);
        String[] strSPaqqnzt ={"无安全气囊","未弹出","前部弹出","侧部弹出","上部弹出","其他位置弹出（膝盖、空气带等）","联合弹出","未知弹出"};
        SPaqqnzt.setAdapter(new MyAdapter(strSPaqqnzt,getContext()).getAdaper());
//抛出状态
        SPpaochuzhuangta = (Spinner)layoutPeoPel2.findViewById(R.id.sp_paochuzhuangtai);
        String[] strSPpaochuzhuangta ={"未被抛出","部分被抛出","完全被抛出","不可用","未知"};
        SPpaochuzhuangta.setAdapter(new MyAdapter(strSPpaochuzhuangta,getContext()).getAdaper());
//事故发生时状态
        SPsgfsszhuangtai = (Spinner)layoutPeoPel2.findViewById(R.id.sp_sgfsszhuangtai);
        String[] strSPsgfsszhuangtai ={"明显正常","身体受损","情绪化（抑郁、生气、不安等）","生病（不舒服）、昏厥",
                "睡着的或疲乏的","受药物/毒品/酒精影响","其他","未知"};
        SPsgfsszhuangtai.setAdapter(new MyAdapter(strSPsgfsszhuangtai,getContext()).getAdaper());
//怀疑饮酒
        SPyinjiu = (Spinner)layoutPeoPel2.findViewById(R.id.sp_yinjiu);
        String[] strSPyinjiu ={"是","否","未知"};
        SPyinjiu.setAdapter(new MyAdapter2(strSPyinjiu,getContext()).getAdaper());

//毒品类型
        SPduPingLeiXing = (Spinner)layoutPeoPel2.findViewById(R.id.sp_dupinleixing);
        String[] strSPduPingLeiXing ={"无","大麻","可卡因","鸦片","苯丙胺","五氯苯酚","其他控制的物质","其他毒品（不包括事故后的毒品）"};
        SPduPingLeiXing.setAdapter(new MyAdapter(strSPduPingLeiXing,getContext()).getAdaper());
//测试状态
        SPceshizhuangtai = (Spinner)layoutPeoPel2.findViewById(R.id.sp_ceshizhuangtai);
        String[] strSPceshizhuangtai ={"未做测试","拒绝测试","做了测试","未知是否测试"};
        SPceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,getContext()).getAdaper());

        SPceshitype = (Spinner)layoutPeoPel2.findViewById(R.id.sp_ceshitype);
        String[] strSPceshitype ={"无","血液","呼吸","尿液","其他"};
        SPceshitype .setAdapter(new MyAdapter(strSPceshitype,getContext()).getAdaper());
        SPceshiresult = (Spinner) (Spinner)layoutPeoPel2.findViewById(R.id.sp_ceshiresult);
        String[] strSPceshiresult ={"无","阳性","阴性","未知"};
        SPceshiresult .setAdapter(new MyAdapter2(strSPceshiresult,getContext()).getAdaper());

        //酒精测试
        SPJJceshizhuangtai = (Spinner)layoutPeoPel2.findViewById(R.id.jjceshizhuangtai);
        SPJJceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,getContext()).getAdaper());

        SPJJceshitype = (Spinner)layoutPeoPel2.findViewById(R.id.jjceshitype);
        SPJJceshitype .setAdapter(new MyAdapter(strSPceshitype,getContext()).getAdaper());

        SPJJceshiresult = (Spinner) (Spinner)layoutPeoPel2.findViewById(R.id.jjceshiresult);
        String[] strSPceshiresult2 ={"无","血液中酒精浓度测试结果","确认是否含酒精","待定","未知"};
        SPJJceshiresult .setAdapter(new MyAdapter(strSPceshiresult2,getContext()).getAdaper());

        //按钮事件

        btnZhuangTai = (Button) layoutPeoPel2.findViewById(R.id.btn_addzhuangtai);
        btnZhuangTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) layoutPeoPel2.findViewById(R.id.text_zhuangtainums);
                textView.setText("已添加状态数"+(++zhuangtainums));
                str_zhuangTai2 += SPsgfsszhuangtai.getSelectedItem().toString();
            }
        });

    }

   public void refreshXunhao(){
       try {
           SPcheliangxunhao = (Spinner) layoutPeoPel1.findViewById(R.id.sp_cheliangxunhao);
           List<CarData> carDatas = ((MainActivity) getActivity()).getCarData();
           String[] strXunhao = new String[carDatas.size()];
           int i = 0;
           Log.i("data", "data----------------");
           for (CarData c : carDatas) {
               strXunhao[i++] = c.getXunhao() + " " + c.getChepaihao();
               Log.i("data", strXunhao[i - 1]);
           }
           SPcheliangxunhao.setAdapter(new MyAdapter2(strXunhao, getContext()).getAdaper());
       }catch (Exception e) {

       }
   }
    private void initJiaShiRen(View view) {
        SPname = (EditText)layoutPeoPel1.findViewById(R.id.sp_name);
//获取时间对象
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH)+1;
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour=cal.get(Calendar.HOUR_OF_DAY);
        minute=cal.get(Calendar.MINUTE);
        chuShenRi = (TextView) layoutPeoPel1.findViewById(R.id.chushenri);
        chuShenRi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                new DatePickerDialog(view1.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        chuShenRi.setText(i+"年"+(i1+1)+"月"+i2+"日");
                    }
                },year,cal.get(Calendar.MONTH),day).show();
            }
        });
//性别
        SPxingbie = (Spinner)layoutPeoPel1.findViewById(R.id.sp_xingbie);
        String[] strSPxingbie ={"男","女"};
        SPxingbie.setAdapter(new MyAdapter2(strSPxingbie,getContext()).getAdaper());
//身份
        SPshenfentype = (Spinner)layoutPeoPel1.findViewById(R.id.sp_shenfentype);
        String[] strSPshenfentype ={"未知","公务员","公安民警","职员","工人","农民","自主经营者","军人","武警","教师","大（专）学生","中（专）学生","小学生","学前儿童","港澳同胞","华侨","外国人","外来务工者","不在业人员","其他"};
        SPshenfentype.setAdapter(new MyAdapter(strSPshenfentype,getContext()).getAdaper());
//关系
        SPrenyuantype = (Spinner)layoutPeoPel1.findViewById(R.id.sp_renyuantype);
        String[] strSPrenyuantype ={"夫妻","父子/父女","亲人","朋友"};
        SPrenyuantype.setAdapter(new MyAdapter(strSPrenyuantype,getContext()).getAdaper());
//受伤程度
        SPsschengdu = (Spinner)layoutPeoPel1.findViewById(R.id.sp_sszhuangtai);
        String[] strSPsszhuangtai ={"致命伤","重伤","疑似轻微受伤","可能受伤","无明显受伤"};
        SPsschengdu.setAdapter(new MyAdapter(strSPsszhuangtai,getContext()).getAdaper());

        phonenum = (EditText) layoutPeoPel1.findViewById(R.id.phonenum);
//车辆序号
        SPcheliangxunhao = (Spinner)layoutPeoPel1.findViewById(R.id.sp_cheliangxunhao);
        String[] strSPcheliangxunhao ={"暂无"};

        List<CarData> carDatas = ((MainActivity)getActivity()).getCarData();
        String[] strXunhao = new String[carDatas.size()];
        int i=0;
        Log.i("data","data----------------");
        for(CarData c :carDatas){
            strXunhao[i++]=c.getXunhao()+" "+c.getChepaihao();
            Log.i("data",strXunhao[i-1]);
        }
        SPcheliangxunhao.setAdapter(new MyAdapter2(strXunhao,getContext()).getAdaper());
        //SPcheliangxunhao.setAdapter(new MyAdapter(strSPcheliangxunhao,getContext()).getAdaper());
////座位位置
//         SPposition = (Spinner)layoutPeoPel1.findViewById(R.id.sp_position);
//        String[] str ={""};
//        .setAdapter(new MyAdapter(str,getContext()).getAdaper());
//安全约束
        SPysxitong = (Spinner)layoutPeoPel1.findViewById(R.id.sp_ysxitong);
        String[] strSPysxitong ={"无可用",
                "未被使用—机动车使用者","使用肩带和腰带","仅使用肩带","仅使用腰带","使用约束—类型未知",
                "儿童约束系统—前向","儿童约束系统—后向","儿童垫高座椅","儿童约束—类型未知","其他","未知"};
        SPysxitong.setAdapter(new MyAdapter(strSPysxitong,getContext()).getAdaper());
        //头盔使用
        SPtuokuishiyong = (Spinner)layoutPeoPel1.findViewById(R.id.sp_tuokuishiyong);
        String[] strSPtuokuishiyong ={"符合DOT规格的摩托车头盔",
                "除符合DOT规格的其他头盔","未知是否符合DOT规格的头盔","无头盔","未知是否戴头盔"};
        SPtuokuishiyong.setAdapter(new MyAdapter(strSPtuokuishiyong,getContext()).getAdaper());
//安全气囊
        SPaqqnzt = (Spinner)layoutPeoPel1.findViewById(R.id.sp_aqqnzt);
        String[] strSPaqqnzt ={"无可用","未弹出","前部弹出","侧部弹出",
                "上部弹出","其他弹出（膝盖、空气带等）","联合弹出","未知弹出"};
        SPaqqnzt.setAdapter(new MyAdapter(strSPaqqnzt,getContext()).getAdaper());
//抛出状态
        SPpaochuzhuangta = (Spinner)layoutPeoPel1.findViewById(R.id.sp_paochuzhuangtai);
        String[] strSPpaochuzhuangta ={"未被抛出","部分被抛出","完全被抛出","不可用","未知"};
        SPpaochuzhuangta.setAdapter(new MyAdapter(strSPpaochuzhuangta,getContext()).getAdaper());
//事故发生时状态
        SPsgfsszhuangtai = (Spinner)layoutPeoPel1.findViewById(R.id.sp_sgfsszhuangtai);
        final String[] strSPsgfsszhuangtai ={"明显正常","身体受损","情绪化（抑郁、生气、不安等）","生病（不舒服）、昏厥",
                "睡着的或疲乏的","受药物/毒品/酒精影响","其他","未知"};
        SPsgfsszhuangtai.setAdapter(new MyAdapter(strSPsgfsszhuangtai,getContext()).getAdaper());
//怀疑饮酒
        SPyinjiu = (Spinner)layoutPeoPel1.findViewById(R.id.sp_yinjiu);
        String[] strSPyinjiu ={"是","否","未知"};
        SPyinjiu.setAdapter(new MyAdapter2(strSPyinjiu,getContext()).getAdaper());
        //毒品类型
        SPduPingLeiXing = (Spinner)layoutPeoPel1.findViewById(R.id.sp_dupinleixing);
        String[] strSPduPingLeiXing ={"无","大麻","可卡因","鸦片","苯丙胺","五氯苯酚","其他控制的物质","其他毒品（不包括事故后的毒品）"};
        SPduPingLeiXing.setAdapter(new MyAdapter(strSPduPingLeiXing,getContext()).getAdaper());
//测试状态
        SPceshizhuangtai = (Spinner)layoutPeoPel1.findViewById(R.id.sp_ceshizhuangtai);
        String[] strSPceshizhuangtai ={"未做测试","拒绝测试","做了测试","未知是否测试"};
        SPceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,getContext()).getAdaper());

        SPceshitype = (Spinner)layoutPeoPel1.findViewById(R.id.sp_ceshitype);
        String[] strSPceshitype ={"无","血液","呼吸","尿液","其他"};
        SPceshitype .setAdapter(new MyAdapter(strSPceshitype,getContext()).getAdaper());
        SPceshiresult = (Spinner) (Spinner)layoutPeoPel1.findViewById(R.id.sp_ceshiresult);
        String[] strSPceshiresult ={"无","阳性","阴性","未知"};
        SPceshiresult .setAdapter(new MyAdapter2(strSPceshiresult,getContext()).getAdaper());

        jiazhao = (EditText) layoutPeoPel1.findViewById(R.id.jiazhao);

        dengji = (Spinner) layoutPeoPel1.findViewById(R.id.sp_dengji);
        String [] strdengji ={"无","无可用","等级A",
                "等级B",
                "等级C",
                "常规驾照等级","等级M"};
        dengji.setAdapter(new MyAdapter(strdengji,getContext()).getAdaper());
//商业驾照
        shangyejiazhao = (Spinner) layoutPeoPel1.findViewById(R.id.sp_shangyejiazhao);
        String [] strshangyejiazhao ={"不是","是"};
        shangyejiazhao.setAdapter(new MyAdapter2(strshangyejiazhao,getContext()).getAdaper());
//签注
        qianzhu = (Spinner) layoutPeoPel1.findViewById(R.id.sp_qianzhu);
        String [] strqianzhu ={"无/不可用","T—两个/三个拖车","P—客车","N—坦克车","H—危险物品",
                "X—坦克车和危险物品组合","S—校车","其他非商业驾照签注（例如摩托车等。）"};
        qianzhu .setAdapter(new MyAdapter(strqianzhu,getContext()).getAdaper());
// 管限区
        guanxiaqu = (Spinner) layoutPeoPel1.findViewById(R.id.sp_guanxiaqu);
        String [] strguanxiaqu ={"成都","绵阳市","自贡市","攀枝花市","泸州市","德阳市","广元市","内江市","乐山市","资阳市","宜宾市","南充市","达州市","雅安市","阿坝藏族羌族自治州","甘孜藏族自治州","凉山彝族自治州","广安市","巴中市","眉山市"};
        guanxiaqu .setAdapter(new MyAdapter(strguanxiaqu,getContext()).getAdaper());
//超速询问
        chaosuxunwen = (Spinner) layoutPeoPel1.findViewById(R.id.sp_chaosuxunwen);
        String [] strchaosuxunwen ={"无","赛车","超速","相对于当时情况过快","未知"};
        chaosuxunwen.setAdapter(new MyAdapter(strchaosuxunwen,getContext()).getAdaper());
//分心驾驶
        fenxinjiashi = (Spinner) layoutPeoPel1.findViewById(R.id.sp_fenxinjiashi);
        String [] strfenxinjiashi ={"无分心","手动操作电子通讯设备（打字、拨号）","免提电子设备通话","手持电子设备通话","其他电子设备行为","乘客","其他车辆内部干扰（进食、个人卫生等）","车辆外部干扰（包括未指明的外部干扰）","未知的分心"};
        fenxinjiashi .setAdapter(new MyAdapter(strfenxinjiashi,getContext()).getAdaper());
//发生时行为
        sgfssxingwie = (Spinner) layoutPeoPel1.findViewById(R.id.sp_sgfssxingwie);
        String [] strsgfssxingwie ={"无致因行为","驶离行车道","违法占道","闯红灯","违反停止标志","违反其他交通标志","违法其他道路标志","不合理转弯","不合理倒车","不合理穿越","逆行","跟车过近","未在合适车道行驶","鲁莽的或激进的驾驶机动车","疏忽的、不仔细的或不稳定的驾驶机动车","由于风、湿滑的路面、机动车、物体、道路中的非机动车者等突然转向或避让","过度回正/过度转向","其他致因行为","未知"};
        sgfssxingwie .setAdapter(new MyAdapter(strsgfssxingwie,getContext()).getAdaper());
//违法行为
        jtwfxingwei = (Spinner) layoutPeoPel1.findViewById(R.id.sp_jtwfxingwei);
        String [] strjtwfxingwei ={"无违反法规","违法","未知"};
        jtwfxingwei.setAdapter(new MyAdapter(strjtwfxingwei,getContext()).getAdaper());
//驾照限制
        jiazhaoxianzhi = (Spinner) layoutPeoPel1.findViewById(R.id.sp_jiazhaoxianzhi);
        String [] strjiazhaoxianzhi ={"无","需矫正视力","机械设备（特殊的制动、手动控制或者其他适合的设备）","假体协助","自动换挡","车外后视镜","仅限白天驾驶","限于雇佣","学习者的许可限制","中级执照限制","其他限制","仅州内商业驾照","无气闸的机动车","仅军用车辆","除了A级公家车","除了A级和B级公交车","除了牵引式挂车","农用车","其他"};
        jiazhaoxianzhi.setAdapter(new MyAdapter(strjiazhaoxianzhi,getContext()).getAdaper());
        //酒精测试
        SPJJceshizhuangtai = (Spinner)layoutPeoPel1.findViewById(R.id.jjceshizhuangtai);
        SPJJceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,getContext()).getAdaper());

        SPJJceshitype = (Spinner)layoutPeoPel1.findViewById(R.id.jjceshitype);
        SPJJceshitype .setAdapter(new MyAdapter(strSPceshitype,getContext()).getAdaper());

        SPJJceshiresult = (Spinner) (Spinner)layoutPeoPel1.findViewById(R.id.jjceshiresult);
        String[] strSPceshiresult2 ={"无","血液中酒精浓度测试结果","确认是否含酒精","待定","未知"};
        SPJJceshiresult .setAdapter(new MyAdapter(strSPceshiresult2,getContext()).getAdaper());

        //按钮事件
         btnXingWei = (Button) layoutPeoPel1.findViewById(R.id.btn_xingshixingwei);
        btnXingWei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) layoutPeoPel1.findViewById(R.id.xingweishu);
                textView.setText("已添加行为数"+(++xingweinums));
                str_xingWei += sgfssxingwie.getSelectedItem().toString();
            }
        });
         btnZhuangTai = (Button) layoutPeoPel1.findViewById(R.id.btn_addzhuangtai);
         btnWeiFa = (Button) layoutPeoPel1.findViewById(R.id.btn_addweifa);
        btnxianzhi = (Button) layoutPeoPel1.findViewById(R.id.btn_addxianzhi);

        btnZhuangTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) layoutPeoPel1.findViewById(R.id.text_zhuangtainums);
                textView.setText("已添加状态数"+(++zhuangtainums));
                str_zhuangTai += SPsgfsszhuangtai.getSelectedItem().toString();
            }
        });
        btnWeiFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) layoutPeoPel1.findViewById(R.id.text_weifa);
                textView.setText("已添加违法数"+(++weifanum));

                str_weifa += jtwfxingwei.getSelectedItem().toString();
            }
        });
        btnxianzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) layoutPeoPel1.findViewById(R.id.text_xianzhinums);
                textView.setText("已添加限制数"+(++xianzhinum));
            }
        });
    }

    private void init(View view) {


        list_info = (ListView) view.findViewById(R.id.list);
        simpleAdapter=new SimpleAdapter(view.getContext(),getData(),R.layout.tableitem2,new String[]{"name","sex","type"},
                new int[]{R.id.name,R.id.sex,R.id.type});
        list_info.setAdapter(simpleAdapter);

        JiaShiren = (ImageView) view.findViewById(R.id.jiashiren);
        ChengZhuoRen = (ImageView) view.findViewById(R.id.chengzhuoren);
        QiTaRen = (ImageView) view.findViewById(R.id.qitaren);

        JiaShiren.setOnClickListener(this);
        ChengZhuoRen.setOnClickListener(this);
        QiTaRen.setOnClickListener(this);

        layoutPeoPel1  = (LinearLayout) view.findViewById(R.id.layoutjiashirne);
        layoutPeoPel2  = (LinearLayout) view.findViewById(R.id.layoutchengzuoren);
        layoutPeoPel3  = (LinearLayout) view.findViewById(R.id.layoutqitaren);

       layjiashiren = (LinearLayout) view.findViewById(R.id.layjiashiren);
       laychengzuoren = (LinearLayout) view.findViewById(R.id.laychengzhuoren);
       layqitaren = (LinearLayout) view.findViewById(R.id.layqitaren);

        layjiashiren.setOnClickListener(this);
        laychengzuoren.setOnClickListener(this);
        layqitaren.setOnClickListener(this);

        SAVE = (Button) view.findViewById(R.id.save);
        SAVE.setOnClickListener(this);
        peopelAD = (TextView) view.findViewById(R.id.peopeladded);
        //SPname = (EditText) layoutPeoPel1.findViewById(R.id.sp_name);
    }

    private List<Map<String,Object>> getData() {
        datalist =  new ArrayList<Map<String, Object>>();
        for(int i =0;i<5;i++){
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("name","张三");
            map.put("sex","男");
            map.put("type","驾驶员");
            datalist.add(map);
        }
        return datalist;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layjiashiren:
            case R.id.jiashiren:
                weifanum=0;
                xianzhinum=0;
                xingweinums=0;
                zhuangtainums=0;
                TYPE = 1;
                layoutPeoPel1.setVisibility(View.VISIBLE);
                layoutPeoPel2.setVisibility(View.GONE);
                layoutPeoPel3.setVisibility(View.GONE);
                JiaShiren.setImageDrawable(getResources().getDrawable(R.drawable.icon_driver));
                ChengZhuoRen.setImageDrawable(getResources().getDrawable(R.drawable.iconqitaren_off));
                QiTaRen.setImageDrawable(getResources().getDrawable(R.drawable.iconczren_off));
                 initJiaShiRen(view);
                break;
            case R.id.laychengzhuoren:
            case R.id.chengzhuoren:
                TYPE = 2;
                layoutPeoPel1.setVisibility(View.GONE);
                layoutPeoPel2.setVisibility(View.VISIBLE);
                layoutPeoPel3.setVisibility(View.GONE);
                initChengZhuorne(view);
                JiaShiren.setImageDrawable(getResources().getDrawable(R.drawable.icon_driveroff));
                ChengZhuoRen.setImageDrawable(getResources().getDrawable(R.drawable.iconqitaren));
                QiTaRen.setImageDrawable(getResources().getDrawable(R.drawable.iconczren_off));
                break;
            case R.id.layqitaren:
            case R.id.qitaren:
                TYPE = 3;
                layoutPeoPel1.setVisibility(View.GONE);
                layoutPeoPel2.setVisibility(View.GONE);
                layoutPeoPel3.setVisibility(View.VISIBLE);
                initQitaRen(view);
                JiaShiren.setImageDrawable(getResources().getDrawable(R.drawable.icon_driveroff));
                ChengZhuoRen.setImageDrawable(getResources().getDrawable(R.drawable.iconqitaren_off));
                QiTaRen.setImageDrawable(getResources().getDrawable(R.drawable.iconczren));
                break;
            case R.id.save:
                addPeopel();
                break;

        }
    }
    public void addPeopel(){
        showProcessing("保存人员信息中...",2000);
        peopelAdded++;

        switch (TYPE) {
            case 1 :
                savePeople1();
                break;
            case 2:
                savePeople2();
                break;
            case 3:
                savePeople3();
                break;
        }
        peopelAD.setText("已添加人数:"+peopelAdded);
        SPname.setText("");

    }

    /*
    * 保存人员信息
    * 1 驾驶人
    * 2 乘坐人
    * 3 其它人
    * */

    private void savePeople1() {//驾驶人
        try{
            PeopelData2 peopelData2 = new PeopelData2(
                    SPname.getText().toString(),
                    SPxingbie.getSelectedItem().toString(),
                    SPshenfentype.getSelectedItem().toString(),
                    SPrenyuantype.getSelectedItem().toString(),

                    SPsschengdu.getSelectedItem().toString(),
                    phonenum.getText().toString(),
                    SPcheliangxunhao.getSelectedItem().toString(),
                    SPysxitong.getSelectedItem().toString(),

                    SPtuokuishiyong.getSelectedItem().toString(),
                    SPaqqnzt.getSelectedItem().toString(),
                    SPpaochuzhuangta.getSelectedItem().toString(),
                    SPsgfsszhuangtai.getSelectedItem().toString(),

                    SPyinjiu.getSelectedItem().toString(),
                    SPduPingLeiXing.getSelectedItem().toString(),
                    SPceshizhuangtai.getSelectedItem().toString(),
                    SPceshitype.getSelectedItem().toString(),

                    SPceshiresult.getSelectedItem().toString(),
                    jiazhao.getText().toString(),
                    dengji.getSelectedItem().toString(),
                    shangyejiazhao.getSelectedItem().toString(),
                    qianzhu.getSelectedItem().toString(),

                    guanxiaqu.getSelectedItem().toString(),
                    chaosuxunwen.getSelectedItem().toString(),
                    fenxinjiashi.getSelectedItem().toString(),
                    str_xingWei,
                    str_zhuangTai,
                    str_weifa,
                    "无"
            );
            peopel2.add(peopelData2);
//            DBO dbo = new DBO(getContext());
//            dbo.insertToPeopel2(peopelData2,"0001");
//            for(PeopelData2 peopelData21: dbo.getPeopel2("0001")){
//                Log.i("peopeldata","getPeopel2:"+peopelData2.toString());
//            }
            Log.i("peopeldata",peopelData2.toString());
            Log.i("peopeldata",SPname.getText().toString());
            EditText editText = (EditText) layoutPeoPel2.findViewById(R.id.sp_name);
            Log.i("peopeldata",editText.getText().toString());
        }catch (Exception e) {
            showProcessing("错误提醒！！尚未保存车辆信息!!!!");
            String cheliang = "";
            try {
                cheliang =  SPcheliangxunhao.getSelectedItem().toString();
            }catch (Exception e2) {
                cheliang = "";
            }
        }

    }

    private void savePeople2() {//乘坐人
        try {
        PelpelData pelpelData = new PelpelData(
                SPname.getText().toString(),
                SPxingbie.getSelectedItem().toString(),
                SPshenfentype.getSelectedItem().toString(),
                SPrenyuantype.getSelectedItem().toString(),

                SPsschengdu.getSelectedItem().toString(),
                phonenum.getText().toString(),
                xunhao.getSelectedItem().toString(),
                SPysxitong.getSelectedItem().toString(),

                SPtuokuishiyong.getSelectedItem().toString(),
                SPaqqnzt.getSelectedItem().toString(),
                SPpaochuzhuangta.getSelectedItem().toString(),
                str_zhuangTai2,

                SPyinjiu.getSelectedItem().toString(),
                SPduPingLeiXing.getSelectedItem().toString(),
                SPceshizhuangtai.getSelectedItem().toString(),
                SPceshitype.getSelectedItem().toString(),
                SPceshiresult.getSelectedItem().toString()
        );

//            DBO dbo = new DBO(getContext());
//            dbo.insertToPeopel1(pelpelData,"0001");
//            List<PelpelData> pelpelDatas = dbo.getPeopel1("0001");
//            for(PelpelData pelpelData1 : pelpelDatas){
//                Log.i("peopeldata", pelpelData1.toString());
//            }
        peopel1.add(pelpelData);
        Log.i("peopeldata",pelpelData.toString());
        }catch (Exception e) {
            showProcessing("错误提醒！！尚未保存车辆信息!!!!");
        }
    }

    private void savePeople3() {//其它人
        try {
        PeopelData3 peopelData3 = new PeopelData3(
                SPname.getText().toString(),
                SPxingbie.getSelectedItem().toString(),
                SPshenfentype.getSelectedItem().toString(),

                SPrenyuantype.getSelectedItem().toString(),
                SPsschengdu.getSelectedItem().toString(),
                phonenum.getText().toString(),

                SPsgfssxingwei.getSelectedItem().toString(),
                str_xingWei3,
                str_shebei,

                SPaqsbsyzhuangtai.getSelectedItem().toString(),
                SPsgfssposition.getSelectedItem().toString(),
                SPcheliangxunhao.getSelectedItem().toString(),

                SPyinjiu.getSelectedItem().toString(),
                SPduPingLeiXing.getSelectedItem().toString(),
                SPceshizhuangtai.getSelectedItem().toString(),

                SPceshitype.getSelectedItem().toString(),
                SPceshiresult.getSelectedItem().toString()
        );
        peopel3.add(peopelData3);
//            DBO dbo = new DBO(getContext());
//            dbo.insertToPeopel3(peopelData3,"0001");
//            peopelData3.setSPxingbie("11111111111111");
//            peopelData3.setNumber("0001");
//            dbo.updatePeopel3(peopelData3);
//            for(PeopelData3 peopelData21: dbo.getPeopel3("0001")){
//                Log.i("peopeldata","getPeopel2:"+peopelData21.toString());
//                Log.i("peopeldata","getPeopel2:"+peopelData21.getNumber());
//            }
        Log.i("peopeldata",peopelData3.toString());
        }catch (Exception e) {
            showProcessing("错误提醒！！尚未保存车辆信息!!!!");
        }
    }

    public void dismissProcessing() {
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.dismiss();
            processDialog = null;
        }
    }
    public void showProcessing(String hintText) {
        if (processDialog == null) {
            processDialog = new ProgressDialog(getContext());
        }
        processDialog.setMessage(hintText);
        processDialog.setCancelable(true);
        processDialog.show();
    }
    public void showProcessing(final String hintText, final int dur) {
        if (processDialog == null) {
            processDialog = new ProgressDialog(getContext());
        }
        processDialog.setMessage(hintText);
        processDialog.setCancelable(true);
        processDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(dur);
                    processDialog.dismiss();
                    Toast.makeText(getContext(), hintText+"  成功", Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }
            }
        }).start();
    }

    //public
    public List<PeopelData3> getPeopel3(){
        return peopel3;
    }

    public List<PeopelData2> getPeopel2(){
        return peopel2;
    }

    public List<PelpelData> getPeopel1() {
        return peopel1;
    }
}

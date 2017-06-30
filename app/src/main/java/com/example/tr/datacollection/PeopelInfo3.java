package com.example.tr.datacollection;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class PeopelInfo3 extends AppCompatActivity {
    private EditText SPname;
    private Spinner SPxingbie;
    private Spinner SPshenfentype;
    private Spinner SPrenyuantype;

    private Spinner SPsschengdu;

    private EditText phonenum;


    private Spinner SPsgfssxingwei;
    private Spinner SPsgfsqxingwei;
    private Spinner SPsgfsszhuangtai;
    private Spinner SPaqsbsyzhuangtai;
    private Spinner SPsgfssposition;

    private Spinner SPcheliangxunhao;

    private Spinner SPyinjiu;
    private Spinner SPduPingLeiXing;
    private Spinner SPceshizhuangtai;
    private Spinner SPceshitype;
    private Spinner SPceshiresult;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peopel_info3);
        init();
    }

    private void init() {
        SPname = (EditText)findViewById(R.id.sp_name);
//获取时间对象
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH)+1;
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour=cal.get(Calendar.HOUR_OF_DAY);
        minute=cal.get(Calendar.MINUTE);
        chuShenRi = (TextView) findViewById(R.id.chushenri);
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
        SPxingbie = (Spinner)findViewById(R.id.sp_xingbie);
        String[] strSPxingbie ={"男","女"};
        SPxingbie.setAdapter(new MyAdapter(strSPxingbie,PeopelInfo3.this).getAdaper());
//身份
        SPshenfentype = (Spinner)findViewById(R.id.sp_shenfentype);
        String[] strSPshenfentype ={"公务员","公安民警","职员","工人","农民","自主经营者","军人","武警","教师","大（专）学生","中（专）学生","小学生","学前儿童","港澳同胞","华侨","外国人","外来务工者","不在业人员","其他"};
        SPshenfentype.setAdapter(new MyAdapter(strSPshenfentype,PeopelInfo3.this).getAdaper());
//关系
        SPrenyuantype = (Spinner)findViewById(R.id.sp_renyuantype);
        String[] strSPrenyuantype ={"无","夫妻","父子/父女","亲人","朋友"};
        SPrenyuantype.setAdapter(new MyAdapter(strSPrenyuantype,PeopelInfo3.this).getAdaper());
//受伤程度
        SPsschengdu = (Spinner)findViewById(R.id.sp_sszhuangtai);
        String[] strSPsszhuangtai ={"致命伤","疑似严重受伤","疑似轻微受伤","可能受伤","无明显受伤"};
        SPsschengdu.setAdapter(new MyAdapter(strSPsszhuangtai,PeopelInfo3.this).getAdaper());

        phonenum = (EditText) findViewById(R.id.phonenum);
//车辆序号
        SPcheliangxunhao = (Spinner)findViewById(R.id.zsjdcxunhao);
        String[] strSPcheliangxunhao ={""};
        SPcheliangxunhao.setAdapter(new MyAdapter(strSPcheliangxunhao,PeopelInfo3.this).getAdaper());

//事故发生时状态
        SPsgfsszhuangtai = (Spinner)findViewById(R.id.sp_sgfsszhuangtai);
        String[] strSPsgfsszhuangtai ={"明显正常","身体受损","情绪化（抑郁、生气、不安等）","生病（不舒服）、昏厥",
                "睡着的或疲乏的","受药物/毒品/酒精影响","其他","未知"};
        SPsgfsszhuangtai.setAdapter(new MyAdapter(strSPsgfsszhuangtai,PeopelInfo3.this).getAdaper());
//怀疑饮酒
        SPyinjiu = (Spinner)findViewById(R.id.sp_yinjiu);
        String[] strSPyinjiu ={"是","否","未知"};
        SPyinjiu.setAdapter(new MyAdapter(strSPyinjiu,PeopelInfo3.this).getAdaper());

//事故发生是状态
        SPsgfssxingwei = (Spinner) findViewById(R.id.sp_sgfssxingwei);
        String[] strSPsgfssxingwei = {"无","穿越道路","等待穿越道路","顺着交通流方向沿着道路步行/骑车（在或邻着行车道）","逆着交通流方向沿着道路步行/骑车（在或邻着行车道）","在人行道上步行/骑车","在车行道上—其他","邻着车行道（例如，路肩，路中）","在道路中作业（应急响应）","其他","无","未知"};
        SPsgfssxingwei.setAdapter(new MyAdapter(strSPsgfssxingwei,this).getAdaper());

        SPsgfsqxingwei = (Spinner) findViewById(R.id.sp_sgfsqxingwei);
        String[] strSPsgfsqxingwei = {"不合适行为","飞奔/猛冲","违法占道","违反交通标志、信号灯、交警","在行车道不合适行为（站立、躺、工作、玩耍）","处理残缺车辆（工作、推、离开/到达）","进/出停止的车辆/站立在车辆上","疏忽（说话、进食等）","不明显可见（黑衣服、无照明等）","不合适的转向/合流","不合适的通行","逆向骑行或步行","其他","未知"};
        SPsgfsqxingwei.setAdapter(new MyAdapter(strSPsgfsqxingwei,this).getAdaper());

        SPaqsbsyzhuangtai = (Spinner) findViewById(R.id.sp_aqsbsyzhuangtai);
        String[] strSPaqsbsyzhuangtai = {"无","头盔","使用保护垫（肘、膝盖、小腿等）","反光衣物（夹克、双肩包等）","照明","其他","无可适用"};
        SPaqsbsyzhuangtai .setAdapter(new MyAdapter(strSPaqsbsyzhuangtai,this).getAdaper());

        SPsgfssposition = (Spinner) findViewById(R.id.sgfssposition);
        String[] strSPsgfssposition = {"十字路口—有标志的人行横道","十字路口—无标志的人行横道","十字路口—其他","路中—有标志的人行横道","车行道—其他位置","自行车道","路肩/路边","人行道","中央分隔带/安全岛","进入车道","公用路径或小道 ","无行车道区域","其他","未知"};
        SPsgfssposition.setAdapter(new MyAdapter(strSPsgfssposition,this).getAdaper());



//毒品类型
        SPduPingLeiXing = (Spinner)findViewById(R.id.sp_dupinleixing);
        String[] strSPduPingLeiXing ={"无","大麻","可卡因","鸦片","苯丙胺","五氯苯酚","其他控制的物质","其他毒品（不包括事故后的毒品）"};
        SPduPingLeiXing.setAdapter(new MyAdapter(strSPduPingLeiXing,PeopelInfo3.this).getAdaper());
//测试状态
        SPceshizhuangtai = (Spinner)findViewById(R.id.sp_ceshizhuangtai);
        String[] strSPceshizhuangtai ={"未做测试","拒绝测试","做了测试","未知是否测试"};
        SPceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,PeopelInfo3.this).getAdaper());

        SPceshitype = (Spinner)findViewById(R.id.sp_ceshitype);
        String[] strSPceshitype ={"血液","呼吸","尿液","其他"};
        SPceshitype .setAdapter(new MyAdapter(strSPceshitype,PeopelInfo3.this).getAdaper());
        SPceshiresult = (Spinner) (Spinner)findViewById(R.id.sp_ceshiresult);
        String[] strSPceshiresult ={"阳性","阴性","未知"};
        SPceshiresult .setAdapter(new MyAdapter(strSPceshiresult,PeopelInfo3.this).getAdaper());
        //酒精测试
        SPJJceshizhuangtai = (Spinner)findViewById(R.id.jjceshizhuangtai);
        SPJJceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,PeopelInfo3.this).getAdaper());

        SPJJceshitype = (Spinner)findViewById(R.id.jjceshitype);
        SPJJceshitype .setAdapter(new MyAdapter(strSPceshitype,PeopelInfo3.this).getAdaper());

        SPJJceshiresult = (Spinner) (Spinner)findViewById(R.id.jjceshiresult);
        String[] strSPceshiresult2 ={"血液中酒精浓度测试结果","确认是否含酒精","待定","未知"};
        SPJJceshiresult .setAdapter(new MyAdapter(strSPceshiresult2,PeopelInfo3.this).getAdaper());
    }
    public void back(View view){
        finish();
    }
    public void add(View view){
        finish();
    }
}

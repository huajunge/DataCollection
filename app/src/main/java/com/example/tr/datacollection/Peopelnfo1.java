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

public class Peopelnfo1 extends AppCompatActivity {
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
        setContentView(R.layout.peopelinfo1);
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
        SPxingbie.setAdapter(new MyAdapter(strSPxingbie,Peopelnfo1.this).getAdaper());
//身份
        SPshenfentype = (Spinner)findViewById(R.id.sp_shenfentype);
        String[] strSPshenfentype ={"公务员","公安民警","职员","工人","农民","自主经营者","军人","武警","教师","大（专）学生","中（专）学生","小学生","学前儿童","港澳同胞","华侨","外国人","外来务工者","不在业人员","其他"};
        SPshenfentype.setAdapter(new MyAdapter(strSPshenfentype,Peopelnfo1.this).getAdaper());
//关系
         SPrenyuantype = (Spinner)findViewById(R.id.sp_renyuantype);
        String[] strSPrenyuantype ={"夫妻","父子/父女","亲人","朋友"};
        SPrenyuantype.setAdapter(new MyAdapter(strSPrenyuantype,Peopelnfo1.this).getAdaper());
//受伤程度
        SPsschengdu = (Spinner)findViewById(R.id.sp_sszhuangtai);
        String[] strSPsszhuangtai ={"致命伤","疑似严重受伤","疑似轻微受伤","可能受伤","无明显受伤"};
        SPsschengdu.setAdapter(new MyAdapter(strSPsszhuangtai,Peopelnfo1.this).getAdaper());

         phonenum = (EditText) findViewById(R.id.phonenum);
//车辆序号
         SPcheliangxunhao = (Spinner)findViewById(R.id.sp_cheliangxunhao);
        String[] strSPcheliangxunhao ={""};
        SPcheliangxunhao.setAdapter(new MyAdapter(strSPcheliangxunhao,Peopelnfo1.this).getAdaper());
////座位位置
//         SPposition = (Spinner)findViewById(R.id.sp_position);
//        String[] str ={""};
//        .setAdapter(new MyAdapter(str,Peopelnfo1.this).getAdaper());
//安全约束
         SPysxitong = (Spinner)findViewById(R.id.sp_ysxitong);
        String[] strSPysxitong ={"无可用",
                "未被使用—机动车使用者","使用肩带和腰带","仅使用肩带","仅使用腰带","使用约束—类型未知",
                "儿童约束系统—前向","儿童约束系统—后向","儿童垫高座椅","儿童约束—类型未知","其他","未知"};
        SPysxitong.setAdapter(new MyAdapter(strSPysxitong,Peopelnfo1.this).getAdaper());
     //头盔使用
         SPtuokuishiyong = (Spinner)findViewById(R.id.sp_tuokuishiyong);
        String[] strSPtuokuishiyong ={"符合DOT规格的摩托车头盔",
                "除符合DOT规格的其他头盔","未知是否符合DOT规格的头盔","无头盔","未知是否戴头盔"};
        SPtuokuishiyong.setAdapter(new MyAdapter(strSPtuokuishiyong,Peopelnfo1.this).getAdaper());
//安全气囊
         SPaqqnzt = (Spinner)findViewById(R.id.sp_aqqnzt);
        String[] strSPaqqnzt ={"无安全气囊","未弹出","前部弹出","侧部弹出","上部弹出","其他位置弹出（膝盖、空气带等）","联合弹出","未知弹出"};
        SPaqqnzt.setAdapter(new MyAdapter(strSPaqqnzt,Peopelnfo1.this).getAdaper());
//抛出状态
         SPpaochuzhuangta = (Spinner)findViewById(R.id.sp_paochuzhuangtai);
        String[] strSPpaochuzhuangta ={"未被抛出","部分被抛出","完全被抛出","不可用","未知"};
        SPpaochuzhuangta.setAdapter(new MyAdapter(strSPpaochuzhuangta,Peopelnfo1.this).getAdaper());
//事故发生时状态
         SPsgfsszhuangtai = (Spinner)findViewById(R.id.sp_sgfsszhuangtai);
        String[] strSPsgfsszhuangtai ={"明显正常","身体受损","情绪化（抑郁、生气、不安等）","生病（不舒服）、昏厥",
                "睡着的或疲乏的","受药物/毒品/酒精影响","其他","未知"};
        SPsgfsszhuangtai.setAdapter(new MyAdapter(strSPsgfsszhuangtai,Peopelnfo1.this).getAdaper());
//怀疑饮酒
         SPyinjiu = (Spinner)findViewById(R.id.sp_yinjiu);
        String[] strSPyinjiu ={"是","否","未知"};
        SPyinjiu.setAdapter(new MyAdapter(strSPyinjiu,Peopelnfo1.this).getAdaper());

//毒品类型
        SPduPingLeiXing = (Spinner)findViewById(R.id.sp_dupinleixing);
        String[] strSPduPingLeiXing ={"无","大麻","可卡因","鸦片","苯丙胺","五氯苯酚","其他控制的物质","其他毒品（不包括事故后的毒品）"};
        SPduPingLeiXing.setAdapter(new MyAdapter(strSPduPingLeiXing,Peopelnfo1.this).getAdaper());
//测试状态
        SPceshizhuangtai = (Spinner)findViewById(R.id.sp_ceshizhuangtai);
        String[] strSPceshizhuangtai ={"未做测试","拒绝测试","做了测试","未知是否测试"};
        SPceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,Peopelnfo1.this).getAdaper());

         SPceshitype = (Spinner)findViewById(R.id.sp_ceshitype);
        String[] strSPceshitype ={"血液","呼吸","尿液","其他"};
        SPceshitype .setAdapter(new MyAdapter(strSPceshitype,Peopelnfo1.this).getAdaper());
         SPceshiresult = (Spinner) (Spinner)findViewById(R.id.sp_ceshiresult);
        String[] strSPceshiresult ={"阳性","阴性","未知"};
        SPceshiresult .setAdapter(new MyAdapter(strSPceshiresult,Peopelnfo1.this).getAdaper());

        //酒精测试
        SPJJceshizhuangtai = (Spinner)findViewById(R.id.jjceshizhuangtai);
        SPJJceshizhuangtai.setAdapter(new MyAdapter(strSPceshizhuangtai,Peopelnfo1.this).getAdaper());

        SPJJceshitype = (Spinner)findViewById(R.id.jjceshitype);
        SPJJceshitype .setAdapter(new MyAdapter(strSPceshitype,Peopelnfo1.this).getAdaper());

        SPJJceshiresult = (Spinner) (Spinner)findViewById(R.id.jjceshiresult);
        String[] strSPceshiresult2 ={"血液中酒精浓度测试结果","确认是否含酒精","待定","未知"};
        SPJJceshiresult .setAdapter(new MyAdapter(strSPceshiresult2,Peopelnfo1.this).getAdaper());
    }

    public void back(View view){
        finish();
    }
    public void add(View view){
        finish();
    }
}

package com.example.tr.datacollection;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.example.tr.datacollection.model.AcccidentData;
import com.example.tr.datacollection.model.CarData;
import com.example.tr.datacollection.model.EMdata;
import com.example.tr.datacollection.model.PelpelData;
import com.example.tr.datacollection.model.PeopelData2;
import com.example.tr.datacollection.model.SimpleDataTest;
import com.example.tr.datacollection.util.DBO;
import com.example.tr.datacollection.util.MyProcessDialog;
import com.example.tr.datacollection.util.SendDataToServer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView frg1;
    private ImageView frg2;
    private ImageView frg3;
    private ImageView frg4;
    private TextView tile;
    private String[] s_renyuantype ={"驾驶人","乘坐人","其他涉及人员"};
    private Context context;
    private AlertDialog dialog = null;
    ViewPager vp_main;
    FragmentPagerAdapter fAdapter;
    List<Fragment> mFrags;

    private AcccidentData acccidentData = new AcccidentData();
    private EMdata eMdata = new EMdata();
    private List<CarData> carDatas = new ArrayList<CarData>();
    private PelpelData pelpelData1 = new PelpelData();
    private PeopelData2 peopelData2 =new PeopelData2();
    private PeopelData3 peopelData3 = new PeopelData3();

    private int carsOrder =1;

    //上传线程
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SendDataToServer.SEND_SUCCESS:
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    break;
                case SendDataToServer.SEND_FAIL:
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tile = (TextView) findViewById(R.id.title);//标题

        frg1 = (ImageView) findViewById(R.id.frag_em);
        frg2 = (ImageView) findViewById(R.id.frag_car);
        frg3 = (ImageView) findViewById(R.id.frag_accident);
        frg4 = (ImageView) findViewById(R.id.frag_driver);
        frg1.setOnClickListener(this);
        frg2.setOnClickListener(this);
        frg3.setOnClickListener(this);
        frg4.setOnClickListener(this);
        vp_main = (ViewPager) findViewById(R.id.vpmain);
        mFrags = new ArrayList<>();
        mFrags.add(new AccidentInfo());
        mFrags.add(new EmInfo());
        mFrags.add(new CarInfo());

        mFrags.add(new DriverInfo());

        mFrags.get(0);
        selectFragment(0);
        fAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFrags.get(position);
            }

            @Override
            public int getCount() {
                return mFrags.size();
            }
        };

        vp_main.setAdapter(fAdapter);
        vp_main.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vp_main.setCurrentItem(position);
               selectFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void selectFragment(int position){
        switch (position){
            case 1:
                frg1.setBackground(getResources().getDrawable(R.drawable.icon_em_on));
                frg2.setBackground(getResources().getDrawable(R.drawable.icon_car_off));
                frg3.setBackground(getResources().getDrawable(R.drawable.icon_accident_off));
                frg4.setBackground(getResources().getDrawable(R.drawable.icon_driver_off));
                tile.setText("环境信息");
//                AccidentInfo accidentInfo = (AccidentInfo) mFrags.get(0);
//                Toast.makeText(this,accidentInfo.GetCITY()+"",Toast.LENGTH_LONG).show();
                break;
            case 2:
                frg1.setBackground(getResources().getDrawable(R.drawable.icon_em));
                frg2.setBackground(getResources().getDrawable(R.drawable.icon_car_on));
                frg3.setBackground(getResources().getDrawable(R.drawable.icon_accident_off));
                frg4.setBackground(getResources().getDrawable(R.drawable.icon_driver_off));
                tile.setText("车辆信息");
                break;
            case 0:
                frg1.setBackground(getResources().getDrawable(R.drawable.icon_em));
                frg2.setBackground(getResources().getDrawable(R.drawable.icon_car_off));
                frg3.setBackground(getResources().getDrawable(R.drawable.icon_accident_on));
                frg4.setBackground(getResources().getDrawable(R.drawable.icon_driver_off));
                tile.setText("事故信息");
                break;
            case 3:
                frg1.setBackground(getResources().getDrawable(R.drawable.icon_em));
                frg2.setBackground(getResources().getDrawable(R.drawable.icon_car_off));
                frg3.setBackground(getResources().getDrawable(R.drawable.icon_accident_off));
                frg4.setBackground(getResources().getDrawable(R.drawable.icon_driver_on));
                tile.setText("人员信息");
                Spinner spinnerXunhao = (Spinner) findViewById(R.id.sp_cheliangxunhao);
                String[] strXunhao = new String[carDatas.size()];
                int i=0;
                for(CarData c :carDatas){
                    strXunhao[i++]=c.getXunhao()+" "+c.getChepaihao();
                }
             //   Intent intent = new Intent()
                try{
                    spinnerXunhao.setAdapter(new MyAdapter2(strXunhao,MainActivity.this).getAdaper());
                }catch (Exception e){

                }



                break;
        }
        vp_main.setCurrentItem(position);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.frag_em:
                selectFragment(1);
                break;
            case R.id.frag_car:
                selectFragment(2);
                break;
            case R.id.frag_accident:
                selectFragment(0);
                break;
            case R.id.frag_driver:
                selectFragment(3);
                break;

        }
    }
    public void addPeopel(final View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新增人员类型");

        builder.setSingleChoiceItems(s_renyuantype,0,new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:Intent intent = new Intent(view.getContext(),PeopelInfo2.class);
                        startActivity(intent);

                        break;
                    case 1:Intent intent2 = new Intent(view.getContext(),Peopelnfo1.class);
                        startActivity(intent2);

                        break;
                    case 2:Intent intent3 = new Intent(view.getContext(),PeopelInfo3.class);
                        startActivity(intent3);

                        break;
                }
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }
    public void updateInfo(final View view){
        AccidentInfo accidentInfo = (AccidentInfo) mFrags.get(0);
//                Toast.makeText(this,accidentInfo.GetCITY()+"",Toast.LENGTH_LONG).show();
      //  acccidentData.setCity(accidentInfo.GetCITY());
        final Date date = accidentInfo.getCal().getTime();
      //  date.setYear();
        //经纬度
        String[] latlngs = accidentInfo.Getjingweidu().split(",");
        LatLng latLng = new LatLng(Double.valueOf(latlngs[1]),Double.valueOf(latlngs[0]));

        acccidentData= new AcccidentData(date, accidentInfo.GetCITY(),accidentInfo.GetCOUNTRY(), accidentInfo.Getxiangzhen(), latLng,
                accidentInfo.GetdimingBeiZhu(), accidentInfo.Getspshigu(), accidentInfo.GetspshiguType(), accidentInfo.Getspshigu2(), accidentInfo.GetspshiguType2(),
                accidentInfo.Getspyanzhongcd(), accidentInfo.Getcarnum(), accidentInfo.GetdriverNums(), accidentInfo.GetFeidriverNums(),accidentInfo.Getssrenshu(),
                accidentInfo.GetdieNums(), accidentInfo.Getspgongjiao(), accidentInfo.Getspzhaoshity(), accidentInfo.weiX, accidentInfo.GetWeixianbz(),
                accidentInfo.Getspfromweixianbz(), accidentInfo.getRyouhaiwuzhi());
        Log.i("data",acccidentData.toString());
        LayoutInflater inflater = LayoutInflater.from (this);
        View view2 = inflater.inflate(R.layout.updata,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认上传？");
      //  builder.setView(view2);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"上传成功",Toast.LENGTH_LONG).show();
                DBO dbo =new DBO(MainActivity.this);

                dbo.insertToSimpleTest(new SimpleDataTest(acccidentData.getLnglat().latitude,
                        acccidentData.getLnglat().longitude,date.getTime(),acccidentData.getCity()+acccidentData.getXianQu()+acccidentData.getShangQuan()+"  "+acccidentData.getDiMingBeiZhu(),acccidentData.getYanZhongCd()));

                List<SimpleDataTest> simpleDataTests = dbo.getsimpleDataTest();
                for(SimpleDataTest s:simpleDataTests){
                    Log.i("simpleDataTests",s.toString()+"------------");
                }
                dialog.dismiss();

                MyProcessDialog myProcessDialog = new MyProcessDialog(MainActivity.this);
                myProcessDialog.showProcessing("正在上传...",2000);
                //JSONObject jsonObject = new JSONWriter(acccidentData);
              //  JSONObject jsonObject = new JSONObject((Map) acccidentData);
             //   jsonObject.
             //   JSONObject jsonObject = JSONObject.fromObject(acccidentData);
              //  new SendDataToServer(handler,"http://192.168.1.100:8080/DataCollectionServer/servlet/AccidentInfoServer").SendDataToServer(jsonObject.toString());
            }
        });
        dialog = builder.create();
        dialog.show();
    }
    public void addNewCar(View view){
        //mFrags.set(2,new CarInfo());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确定保存？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                EditText editTextVin = (EditText) MainActivity.this.findViewById(R.id.vin);
                EditText editTextChepaiHao = (EditText) MainActivity.this.findViewById(R.id.chepaihao);
                AccidentInfo accidentInfo = (AccidentInfo) mFrags.get(0);
               // int cs = Integer.parseInt(accidentInfo.Getcarnum());
                String[] str = accidentInfo.Getcarnum().split("[^\\D]");
                Log.i("cars","carsnum"+str[0]);
                Log.i("cars",str[0]+"  :  "+carsOrder);
                try{
                    BigInteger cs = trimToNumber(accidentInfo.Getcarnum());
                    if(carsOrder>cs.intValue()){
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
//                        builder.setTitle("已完成添加");
//                        dialog = builder.create();
//                        dialog.show();
                        Toast.makeText(MainActivity.this,"添加失败，已添加完成所有车辆信息！",Toast.LENGTH_LONG).show();
                        Log.i("cars",str[0]+"  :  "+carsOrder);
                        return;
                    }
                }catch (Exception e){
                        e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                CarData carData=new CarData();
                TextView textView = (TextView) findViewById(R.id.carorder);
                carData.setXunhao(carsOrder);
                carData.setChepaihao(editTextChepaiHao.getText().toString());
                carDatas.add(carData);
                BigInteger cs = trimToNumber(accidentInfo.Getcarnum());
                if(carsOrder == cs.intValue()){
                    Toast.makeText(MainActivity.this,"已添加完成所有车辆信息！",Toast.LENGTH_LONG).show();
                    ++carsOrder;
                    return;
                }
                textView.setText("当前车辆序号:"+(++carsOrder));
                editTextChepaiHao.setText("");
                editTextVin.setText("");
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();
    }
    public static BigInteger trimToNumber(String s) {
        int n = s.length();
        char[] a = new char[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                a[len++] = ch;
            }
        }
        return new BigInteger(new String(a, 0, len));
    }

    public static void main(String[] args) {
        String str = "123、4567/885*54666+8874";
        BigInteger n = trimToNumber(str);
        System.out.println(n);
    }
    public List<CarData> getCarData(){
        return carDatas;
    }
}

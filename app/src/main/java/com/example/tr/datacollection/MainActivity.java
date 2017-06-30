package com.example.tr.datacollection;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.example.tr.datacollection.model.AcccidentData;
import com.example.tr.datacollection.model.CarData;
import com.example.tr.datacollection.model.EMdata;
import com.example.tr.datacollection.model.PelpelData;
import com.example.tr.datacollection.model.PeopelData2;

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
        Date date = accidentInfo.getCal().getTime();
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
        builder.setTitle("上传数据");
        builder.setView(view2);

        dialog = builder.create();
        dialog.show();
    }
    public void addNewCar(View view){
        mFrags.set(2,new CarInfo());
        
    }
}

package com.example.tr.datacollection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.tr.datacollection.model.SimpleDataTest;
import com.example.tr.datacollection.util.DBO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Analysis extends AppCompatActivity implements AMapLocationListener, AMap.OnMapClickListener, AMap.OnMarkerClickListener {
    private AMap aMap;
    private MapView mapView;
    private List<Marker> markers = new ArrayList<Marker>();
    private boolean LOCATION_KO=false;
    public AMapLocationClientOption mapLocationClientOption = null;
    public AMapLocationClient mapLocationClient;
    //
    private List<SimpleDataTest> SimpleDataTests = new ArrayList<SimpleDataTest>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        initView();
        mapView.onCreate(savedInstanceState);   //必须重写
    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.mapView);
        aMap = mapView.getMap();
        DBO dbo = new DBO(this);

        SimpleDataTests = dbo.getsimpleDataTest();

        initMarker();
        mapLocationClient = new AMapLocationClient(Analysis.this);
        mapLocationClientOption = new AMapLocationClientOption();
        //设置定位监听
        mapLocationClient.setLocationListener(this);
        //设置定位模式为高精度模式
        mapLocationClientOption.setLocationMode(AMapLocationClientOption
                .AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒
        mapLocationClientOption.setInterval(200);
        mapLocationClient.setLocationOption(mapLocationClientOption);
        //启动定位
        mapLocationClient.startLocation();
        aMap.setOnMapClickListener(this);
        aMap.setOnMarkerClickListener(this);
    }

    private void initMarker() {
        for(SimpleDataTest s:SimpleDataTests){
            LatLng latLng = new LatLng(s.getLat(),s.getLng());
            Marker marker = aMap.addMarker(new MarkerOptions().position(latLng));
            SimpleDateFormat simplef = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            marker.setTitle("时间:"+simplef.format(s.getDate()));
            marker.setSnippet("地点:"+s.getPlace()+"\n"+"严重程度:"+s.getYanzhongchengdu());
            markers.add(marker);
        }
    }
    private void moveToLocation(double lat, double lng) {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),aMap.getCameraPosition().zoom));
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if(amapLocation !=null){
            if(amapLocation.getErrorCode() == 0){

                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
               // this.myLocation = new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
                if(!LOCATION_KO){
                    moveToLocation(amapLocation.getLatitude(),amapLocation.getLongitude());
//                    marker = aMap.addMarker(new MarkerOptions().position(this.myLocation)
//                            .icon(BitmapDescriptorFactory
//                                    .fromResource(R.drawable.place_marker)));
//                    marker.setDraggable(true);
                    LOCATION_KO = true;
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        for(int i =0;i<markers.size();i++){
            if(marker.equals(markers.get(i))){
                marker.showInfoWindow();
            }
        }
        return false;
    }
    public void back(View view){
        finish();
    }
}

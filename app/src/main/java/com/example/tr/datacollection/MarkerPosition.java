package com.example.tr.datacollection;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MarkerPosition extends AppCompatActivity implements AMap.OnMarkerDragListener, PoiSearch.OnPoiSearchListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, AMap.OnCameraChangeListener, AMapLocationListener {
    private static final String TAG = "MarkerPosition";
    private static final int REQUEST_PERMISSION_CODE = 1;

    private AMap aMap;
    private MapView mapView;
    private LatLng position;
    private LatLng orginPosition;
    private Marker marker;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private ListView poiList;
    private List<Map<String,Object>> dataList;
    private SimpleAdapter simpleAdapter;
    private LatLng myLocation;
    public Context context;
    public AMapLocationClientOption mapLocationClientOption = null;
    public AMapLocationClient mapLocationClient;
    private  int TYPE = 0;
    private boolean LOCATION_KO=false;
    private TextView textViewCity;
    private TextView textViewXingzhengqu;
    private TextView textViewShangquan;
    private Spinner spPlaceName;
    private EditText editTextPlaceBeizhu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_position);
//        DBO dbo = new DBO(this);
//        dbo.clearTest();
        mapView = (MapView) findViewById(R.id.mapView);
        aMap = mapView.getMap();
        mapView.onCreate(savedInstanceState);   //必须重写
        checkPermissions();
    }

    private void checkPermissions() {
        Log.i(TAG, "checkPermissions: 2");
        List<String> permissionList = new ArrayList<>();    //需要申请的权限列表
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// >= 23
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(Manifest.permission.RECORD_AUDIO);
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(Manifest.permission.CAMERA);
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(Manifest.permission.READ_CONTACTS);
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(Manifest.permission.READ_PHONE_STATE);
            if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(Manifest.permission.RECEIVE_SMS);

            if (permissionList.size() == 0) {
                init();
            } else {//询问请求权限
                Log.i(TAG, "checkPermissions: " + permissionList);
                requestPermissions(permissionList.toArray(new String[permissionList.size()]), REQUEST_PERMISSION_CODE);
            }
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                boolean all = true;
                if(grantResults.length > 0 ){
                    for(int result:grantResults)
                    {
                        if(result != PackageManager.PERMISSION_GRANTED){
                            all = false;
                            break;
                        }
                    }
                }
                if(!all)
                    Toast.makeText(this, "未获得权限，功能无法正常使用！", Toast.LENGTH_SHORT).show();
                else init();
                break;
        }
    }

    private void moveToLocation(double lat, double lng) {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),aMap.getCameraPosition().zoom));
    }

    private void init() {
        //初始化控件
          textViewCity = (TextView) findViewById(R.id.city);
          textViewXingzhengqu = (TextView) findViewById(R.id.xingzhengqu);
          textViewShangquan = (TextView) findViewById(R.id.shangquan);
          spPlaceName = (Spinner) findViewById(R.id.place_name);
          editTextPlaceBeizhu = (EditText) findViewById(R.id.place_beizhu);

        mapLocationClient = new AMapLocationClient(MarkerPosition.this);
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
        position = new LatLng(getIntent().getDoubleExtra("Lat",0f), getIntent().getDoubleExtra("Lng",0f));
        //Log.i("sp","---in-------"+getIntent().getDoubleExtra("startLat2",0f)+"  ori   "+orginPosition.latitude+" ddd  "+getIntent().getDoubleExtra("Lat",0f));
        aMap.setOnMarkerDragListener(this);
        aMap.setOnCameraChangeListener(this);

        //Poi
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        setPoi("",position);
        poiList = (ListView) findViewById(R.id.pois);
        poiList.setOnItemClickListener(this);
        poiList.setOnItemSelectedListener(this);
        dataList = new ArrayList<Map<String,Object>>();
        TYPE = getIntent().getIntExtra("type",0);
        Log.i("POSITION_TYPE","MK-----:"+TYPE);
        Log.i("POSITION_TYPE","MK-||||||||||||----:"+aMap.getCameraPosition().target.latitude);
    }

    private void setPoi(String keyWord,LatLng lc) {
        query = new PoiSearch.Query(keyWord,"地名地址信息|交通地名|道路名|路口名|街道级地名|交叉口","成都");
        query.setPageSize(20);
        query.setPageNum(1);
        poiSearch = new PoiSearch(MarkerPosition.this,query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(lc.latitude,lc.longitude),1000));
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        position = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
        setPoi("路|道|道路|站|口",new LatLng(marker.getPosition().latitude,marker.getPosition().longitude));
        moveToLocation(marker.getPosition().latitude,marker.getPosition().longitude);
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (!poiResult.getPois().isEmpty()){
            dataList.clear();
            int icon = R.drawable.point_my;
            if( !poiResult.getPois().isEmpty()){
                PoiItem pi = poiResult.getPois().get(0);
                //pi.getBusinessArea()+"   "+ pi.getAdName()+" "+pi.getCityName()+pi.getTitle();
                textViewCity.setText(pi.getCityName());
                textViewShangquan.setText(pi.getBusinessArea());
                textViewXingzhengqu.setText(pi.getAdName());
            }
            String ss ="";
            for(PoiItem pi : poiResult.getPois()){
                String p = pi.getTitle();
                if(p.contains("路")||p.contains("口")||p.contains("道")||p.contains("街")||p.contains("交")){
                    ss+=pi.getTitle()+"---";
                }

                if(!(pi.getBusinessArea().equals("")||pi.getBusinessArea()==null)){
                    textViewShangquan.setText(pi.getBusinessArea());
                }
            }
            spPlaceName.setAdapter(new MyAdapter2(ss.split("---"),MarkerPosition.this).getAdaper());
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startNavi(view);
    }
    public void back(View v) {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        startNavi(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void selected(View v){
        startNavi(v);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        marker.setPosition(cameraPosition.target);
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        marker.setPosition(cameraPosition.target);
        position = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
        setPoi("",new LatLng(marker.getPosition().latitude,marker.getPosition().longitude));
        moveToLocation(marker.getPosition().latitude,marker.getPosition().longitude);
        //marker.setAnimation();
        jumpPoint(marker);
    }
    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        Point startPoint = proj.toScreenLocation(position);
        startPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 1000;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * position.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * position.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                //aMap.invalidate();// 刷新地图
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
    public void startNavi(View view){

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
                this.myLocation = new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
                if(!LOCATION_KO){
                    moveToLocation(amapLocation.getLatitude(),amapLocation.getLongitude());
                    marker = aMap.addMarker(new MarkerOptions().position(this.myLocation)
                            .icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.marker)));
                    marker.setDraggable(true);
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
    //添加事件
    public void addShigu(View view){
        Intent intent = new Intent(MarkerPosition.this,MainActivity.class);
        try{
            intent.putExtra("lng", marker.getPosition().longitude);
            intent.putExtra("lat", marker.getPosition().latitude);
        }catch (Exception e){
            intent.putExtra("lng", 0.0);
            intent.putExtra("lat", 0.0);
        }
         intent.putExtra("city", textViewCity.getText().toString());
         intent.putExtra("country", textViewXingzhengqu.getText().toString());
         intent.putExtra("xiangzhen", textViewShangquan.getText().toString());
     //    intent.putExtra("diming",spPlaceName.getSelectedItem().toString());
        try {
            intent.putExtra("beizhu", spPlaceName.getSelectedItem().toString()+editTextPlaceBeizhu.getText().toString());
        }
        catch (Exception e) {
            intent.putExtra("beizhu", editTextPlaceBeizhu.getText().toString());
        }


         startActivity(intent);

    }
    public void analysis(View view){
        Intent intent = new Intent(MarkerPosition.this,HistoryFuelConsumptionActivity.class);
        startActivity(intent);
    }

    public void moveToCenter(View view) {
        marker.setPosition(myLocation);
        position = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
        setPoi("",new LatLng(marker.getPosition().latitude,marker.getPosition().longitude));
        moveToLocation(marker.getPosition().latitude,marker.getPosition().longitude);
        //marker.setAnimation();
        jumpPoint(marker);
    }
}

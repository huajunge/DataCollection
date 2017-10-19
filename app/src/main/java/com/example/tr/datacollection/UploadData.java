package com.example.tr.datacollection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.tr.datacollection.fragment.NoUploadFragment;
import com.example.tr.datacollection.fragment.UploadFragment;

import java.util.ArrayList;
import java.util.List;

public class UploadData extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;
    FragmentPagerAdapter fragmentPagerAdapter;
    List<Fragment> mFrags;
    ListView listView;
    Button noUploadFragment;
    Button uploadFragment;
    boolean isFirst = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);

        viewPager = (ViewPager) findViewById(R.id.vpmain);
        noUploadFragment = (Button) findViewById(R.id.noupload);
        uploadFragment = (Button) findViewById(R.id.upload);
        noUploadFragment.setOnClickListener(this);
        uploadFragment.setOnClickListener(this);

        mFrags =new ArrayList<>();
        mFrags.add(new NoUploadFragment());
        mFrags.add(new UploadFragment());
        mFrags.get(0);
        selectFragment(0);


        fragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFrags.get(position);
            }


            @Override
            public int getCount() {
                return mFrags.size();
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
                selectFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.noupload :
                selectFragment(0);
                break;
            case R.id.upload:
                selectFragment(1);
                break;
        }
    }

    public void selectFragment(int position){
        viewPager.setCurrentItem(position);


        switch (position) {
            case 0 :
                findViewById(R.id.btn_noupload).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_upload).setVisibility(View.INVISIBLE);
                if(isFirst){
                    NoUploadFragment noUploadFragment = (NoUploadFragment) mFrags.get(0);
                    noUploadFragment.reflash();
                }
                break;
            case 1:
                findViewById(R.id.btn_noupload).setVisibility(View.INVISIBLE);
                findViewById(R.id.btn_upload).setVisibility(View.VISIBLE);
                if(isFirst){
                    UploadFragment uploadFragment = (UploadFragment) mFrags.get(1);
                    uploadFragment.reflash();
                }
                break;
        }
        isFirst = true;
    }
    
}

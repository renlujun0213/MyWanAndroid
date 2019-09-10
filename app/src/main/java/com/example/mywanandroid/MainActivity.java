package com.example.mywanandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mTvTitle;
    private Toolbar mToolBar;
    private FrameLayout mContainer;
    private NavigationView mNv;
    private DrawerLayout mDl;
    private LinearLayout mLl;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mFragments;
    private FragmentManager mManager;
    private int mLastPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mContainer = (FrameLayout) findViewById(R.id.container);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //ToolBar
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);


        //Navigationview
        mNv.setItemIconTintList(null);
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //点击哪个让哪个变为选中状态
                item.setChecked(true);
                return false;
            }
        });

        //ActionBarDrawerToggle

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolBar, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.syncState();

        //侧滑菜单的滑动监听
        mDl.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //slideOffset滑动的百分比
                //抽屉部分滑出多少,主界面向右移动多少
                //抽屉菜单滑动距离
                float x = mNv.getWidth() * slideOffset;
                //设置x方向的位置
                mLl.setX(x);
            }
        });



    }
    /**
     * 创建选项菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

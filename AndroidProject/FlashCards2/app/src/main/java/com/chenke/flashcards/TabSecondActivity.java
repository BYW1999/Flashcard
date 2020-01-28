package com.chenke.flashcards;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.design.widget.TabLayout.OnTabSelectedListener;

import com.chenke.flashcards.adapter.CopyPagerAdapter;

import java.util.ArrayList;

public class TabSecondActivity extends AppCompatActivity implements OnTabSelectedListener {
    private ViewPager vp_content;  //翻页视图
    private TabLayout tab_title;  //标签视图
    private ArrayList<String> mTitleArray = new ArrayList<>();  //标题

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabsecond);
        Toolbar tl_head = findViewById(R.id.tl_head);
        setSupportActionBar(tl_head);
        mTitleArray.add("数字临摹");
        mTitleArray.add("形状临摹");
        initTabLayout();
        initTabViewPager();
    }

    //初始化标签布局
    private void initTabLayout() {
        tab_title = findViewById(R.id.tab_title);
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(0)));
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(1)));
        // 给tab_title添加标签选中监听器，该监听器默认绑定了翻页视图vp_content
        tab_title.addOnTabSelectedListener(this);
    }

    // 初始化标签翻页
    private void initTabViewPager() {
        vp_content = findViewById(R.id.vp_content);
        CopyPagerAdapter adapter = new CopyPagerAdapter(
                getSupportFragmentManager(),mTitleArray);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tab_title.getTabAt(position).select();
            }
        });
    }


    //在标签选中时触发
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vp_content.setCurrentItem(tab.getPosition());
    }

    //在标签取消选中时触发
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    //在标签被重复选中时触发
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

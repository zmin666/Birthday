package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.app.utils.StatusBarCompat;
import com.zmin.birthday.di.component.DaggerMainComponent;
import com.zmin.birthday.di.module.MainModule;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.presenter.MainPresenter;
import com.zmin.birthday.mvp.ui.adapter.BirthdayDataAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.view {

    public static final int REQUEST_DATE = 261;

    @Nullable
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @Nullable
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @Nullable
    @BindView(R.id.srl)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Nullable
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @Nullable
    @BindView(R.id.navigation)
    NavigationView mNavigationView;

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Intent intent = new Intent(this, AddBirthdayActivity.class);
        startActivityForResult(intent, REQUEST_DATE);
    }

    //记录用户首次点击返回键的时间
    private long firstTime = 0;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        //提醒  捐赠  更新
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        setupView();
        addListener();
        mPresenter.requestBirthdayData(true);
    }

    private void setupView() {
        setSupportActionBar(mToolbar);
        //沉浸式状态栏  状态栏颜色
        StatusBarCompat.compat(this, 0xFF0BB375);
        //刷新空间色彩
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout.setTitle("生日备忘录");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void addListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestBirthdayData(true);
            }
        });
        setupDrawerContent(mNavigationView);
    }

    @Override
    public void setAdapter(BirthdayDataAdapter adapter) {
        adapter.setOnItemClickListener(new BirthdayDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Birthday birthday) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("birth", birthday);
                bundle.putInt("position", position);
                Intent intent = new Intent(MainActivity.this, AddBirthdayActivity.class);
                intent.putExtras(bundle);
                launchActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void deleteItem(int position) {
        mPresenter.deleteItemData(position);
    }

    @Override
    public void showLoading() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //可以根据多个请求代码来作相应的操作
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            //获取新的生日
            Bundle bundle = data.getExtras();
            Birthday newBirthday = bundle.getParcelable("NewBirthday");
            if (newBirthday != null) {
                Log.i("zmin.......新加的生日......", "...." + newBirthday.toString());
                mPresenter.addItemData(newBirthday);
            }
        }
    }

    /**
     * 点击了侧边栏
     *
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.user_data:
                                Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
                                launchActivity(intent);
                                break;
                            case R.id.update:
                                break;
                            case R.id.advise:
                                break;
                            case R.id.donate:
                                break;
                            case R.id.about_us:
                                break;
                            default:
                                break;

                        }
                        // 关闭侧滑菜单
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}

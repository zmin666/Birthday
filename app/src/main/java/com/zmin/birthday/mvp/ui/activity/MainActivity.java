package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.app.db.DbCore;
import com.zmin.birthday.app.db.dao.BirthdayDao;
import com.zmin.birthday.app.db.dao.DaoSession;
import com.zmin.birthday.app.userpermission.user.User;
import com.zmin.birthday.app.userpermission.user.UserControl;
import com.zmin.birthday.di.component.DaggerMainComponent;
import com.zmin.birthday.di.module.MainModule;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.view {
    @Nullable
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @Nullable
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @Nullable
    @BindView(R.id.fab_test)
    FloatingActionButton fab_test;
    @Nullable
    @BindView(R.id.srl)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Nullable
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @OnClick(R.id.fab)
    public void onViewClicked() {
        //addItem();
        User currentUser = UserControl.getInstance().getCurrentUser(this);
        Log.i("zmin.............","...." +  currentUser.toString());
    }

    @OnClick(R.id.fab_test)
    public void onViewTextClicked() {
        // 测试获取数
        DaoSession daoSession = DbCore.getDaoSession();
        BirthdayDao birthdayDao = daoSession.getBirthdayDao();
        List<Birthday> list = birthdayDao.queryBuilder()
                .where(BirthdayDao.Properties.Name.eq("张三丰"))
                .orderAsc(BirthdayDao.Properties.Id)
                .limit(2)
                .build().list();




        Log.i("zmin.............","...." +  list);



        //获取网络数据
        mPresenter.getUsers();
    }

    //记录用户首次点击返回键的时间
    private long firstTime=0;



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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout.setTitle("生日记录");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
    }

    private void addListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestBirthdayData(true);
            }
        });
    }

    @Override
    public void setAdapter(DefaultAdapter adapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void addItem() {
        mPresenter.addItemData();
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

    }

    @Override
    public void killMyself() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                firstTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

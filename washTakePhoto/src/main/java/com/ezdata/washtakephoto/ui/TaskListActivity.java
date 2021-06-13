package com.ezdata.washtakephoto.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ezdata.commonlib.Constants.Constant;
import com.ezdata.commonlib.core.BaseActivity;
import com.ezdata.commonlib.core.mvp.MvpView;
import com.ezdata.commonlib.widget.ParallaxPtrFrameLayout;
import com.ezdata.commonlib.widget.TroubleItemDecoration;
import com.ezdata.washtakephoto.R;
import com.ezdata.washtakephoto.adapter.TaskListAdapter;
import com.ezdata.washtakephoto.bean.ListBean;
import com.ezdata.washtakephoto.bean.WashTask;
import com.ezdata.washtakephoto.presenter.WashPresenter;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TaskListActivity extends BaseActivity implements MvpView{


    private final String Status_UnStart= "UnStart";
    private final String Status_Start   = "Start";
    private final String Status_Finish  = "Finish";
    private final String Status_PASS    = "Pass";
    private final String Status_UnStart_Start    = "UnStart+Start";
    private final String Status_ALL    = "All";
    TextView unstartTv;
    TextView allTv;
    TextView finishTv;
    TextView passedTv;
    TextView startingTv;
    TextView startAndUnStart;
    TextView currentStateTv;
    TextView taskNumTv;

    LRecyclerView listTaskRv;
    ParallaxPtrFrameLayout refreshLayout;

    LRecyclerViewAdapter lrvAdapter;
    private TaskListAdapter adapter;
    //列表数据
    private ArrayList<WashTask> dataList;
    private WashPresenter presenter;
    private final int GET_TASK_LIST_CODE = 90;
    private String washCode = "0";

    int pageNum =1;
    int mNextPage = 1;
    private int pageSize = 10;
    private String currentState=Status_UnStart_Start;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        unstartTv = findViewById(R.id.unstartTv);
        startingTv = findViewById(R.id.startingTv);
        finishTv = findViewById(R.id.finishTv);
        passedTv = findViewById(R.id.passedTv);
        allTv = findViewById(R.id.allTv);
        startAndUnStart = findViewById(R.id.startAndUnStart);
        listTaskRv = findViewById(R.id.listTaskRv);
        refreshLayout = findViewById(R.id.refreshLayout);
        currentStateTv = findViewById(R.id.currentStateTv);
        taskNumTv = findViewById(R.id.taskNumTv);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        unstartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNum = 1;
                getTaskIdListData(Status_UnStart);
                currentState = Status_UnStart;

            }
        });
        startingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNum = 1;
                getTaskIdListData(Status_Start);
                currentState = Status_Start;

            }
        });
        finishTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNum = 1;
                getTaskIdListData(Status_Finish);
                currentState = Status_Finish;

            }
        });
        passedTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNum = 1;
                getTaskIdListData(Status_PASS);
                currentState = Status_PASS;

            }
        });
        allTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNum = 1;
                getTaskIdListData(Status_ALL);
                currentState = Status_ALL;

            }
        });
        startAndUnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNum = 1;
                getTaskIdListData(Status_UnStart_Start);
                currentState = Status_UnStart_Start;

            }
        });
        /**
         * 下一页的请求
         */
        listTaskRv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                Log.d("xcq", "onLoadMore:下一页是 "+mNextPage);
                if (mNextPage != -1) {
                    pageNum++;
                    getTaskIdListData(currentState);
                } else {
                    listTaskRv.setNoMore(true);
                }
            }
        });
        lrvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it = new Intent(TaskListActivity.this, TaskDetailsActivity.class);
                //传递一些数据给详情页
                it.putExtra("washTask", dataList.get(position));
                startActivity(it);
            }
        });
    }

    /**
     * 上啦刷新的请求
     */
    private PtrHandler mPtrHandler = new PtrDefaultHandler() {
        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            // 网络请求。
            pageNum = 1;
            // 网络请求。
            getTaskIdListData(currentState);
        }
    };
    private void getTaskIdListData (String status){
        if(status.equals(Status_ALL)){
            presenter.getClothGroupList(pageNum,pageSize,washCode,"","",GET_TASK_LIST_CODE);
            currentStateTv.setText("筛选条件：所有");
        }else{
            presenter.getClothGroupList(pageNum,pageSize,washCode,status,"",GET_TASK_LIST_CODE);
            if(status.equals(Status_UnStart)){
                currentStateTv.setText("筛选条件：未开始");
            }else if(status.equals(Status_Start)){
                currentStateTv.setText("筛选条件：进行中");
            }else if(status.equals(Status_Finish)){
                currentStateTv.setText("筛选条件：已完成");
            }else if(status.equals(Status_PASS)){
                currentStateTv.setText("筛选条件：已审核");
            }else if(status.equals(Status_UnStart_Start)){
                currentStateTv.setText("筛选条件：进行中和未开始");
            }
        }



    }
    @Override
    protected void initData() {
        washCode = getIntent().getStringExtra(Constant.MACHINE_ID);
        setTitle(washCode+"洗衣机采集组合");
        //实例化presenter
        presenter = new WashPresenter();
        presenter.attachView(this);

        //设置adapter
        dataList = new ArrayList<>();
        adapter = new TaskListAdapter(this,dataList);
        lrvAdapter = new LRecyclerViewAdapter(adapter);
        listTaskRv.setLayoutManager(new LinearLayoutManager(this));
        listTaskRv.addItemDecoration(new TroubleItemDecoration());
        listTaskRv.setAdapter(lrvAdapter);

        //初始化LRecyclerView的list
        refreshLayout.setPtrHandler(mPtrHandler);
        listTaskRv.setLayoutManager(new LinearLayoutManager(this));
        listTaskRv.setHasFixedSize(true);
        listTaskRv.setFooterViewHint(getString(R.string.list_loading), getString(R.string.list_nomore), getString(R.string.list_neterror));
        listTaskRv.setFooterViewColor(R.color.blue, R.color.gray_DC, R.color.white_f0f0f0);
        //listPersonRv.setFooterViewColor(R.color.colorLightRed, R.color.blue2_block, R.color.colorBlack);
        listTaskRv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        autoLoad();
    }

    /**
     * autoRefresh会执行onRefreshBegin的方法
     */
    public void autoLoad() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.autoRefresh();
            }
        }, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //autoLoad();
    }

    @Override
    public void onStartRequest(int requestCode) {

    }

    @Override
    public void onSuccess(int requestCode, Object o) {

//        if(dataList.size()>0){
//            dataList.clear();
//        }
//
//        ListBean<WashTask> listbean = (ListBean<WashTask>) o;
//        if(listbean.list.size()>0){
//            dataList.addAll (listbean.list);
//            lrvAdapter.notifyDataSetChanged ();
//            refreshLayout.refreshComplete ();
//        }else{
//            lrvAdapter.notify();
//            listTaskRv.refreshComplete (1);
//        }


        Log.d("TAG", "onSuccess: xcq");
        Log.d("TAG", "onSuccess: xcq2");
        Log.d("TAG", "onSuccess: xcq3");

        ListBean<WashTask> listBean = (ListBean<WashTask>) o;
        //计算总页数
        //计算总页数
        int totalPage = 0;
        taskNumTv.setText("--"+listBean.total+"个");
        if (listBean.total % pageSize == 0) {//可以整除
            totalPage = (listBean.total / pageSize);
        } else {
            totalPage = (listBean.total / pageSize) + 1;
        }

        if (pageNum==1){//首次进入或者是刷新，刷新也需要吗???

            if(dataList.size()>0){
                //如果需要显示第一页，那么吧之前的list清空
                dataList.clear();

            }
            if (listBean.list!=null){
                dataList.addAll (listBean.list);
                lrvAdapter.notifyDataSetChanged();
                mNextPage = pageNum+1;
                if(mNextPage>totalPage){
                    mNextPage=-1;
                }
                //没有数据时候提示
                if(listBean.list==null||listBean.list.size()==0){

                }
            }
            lrvAdapter.notifyDataSetChanged();
            listTaskRv.setNoMore (false);//???回头删掉
            refreshLayout.refreshComplete ();//???
        }else{//加载更多的页面
            mNextPage++;//不懂什么意思的话，自己debug看吧，不能省略
            if (mNextPage>totalPage){
                mNextPage = -1;
            }
            dataList.addAll (listBean.list);
            lrvAdapter.notifyDataSetChanged ();
            listTaskRv.refreshComplete (pageSize);//把一页加载数量加入就正常了???

        }

    }

    @Override
    public void onErrorCode(int resultCode, String msg, int requestCode) {

    }

    @Override
    public void onEndRequest(int requestCode) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

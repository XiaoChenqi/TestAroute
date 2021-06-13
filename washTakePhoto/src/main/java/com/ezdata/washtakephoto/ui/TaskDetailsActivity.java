package com.ezdata.washtakephoto.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.ezdata.commonlib.Constants.Constant;
import com.ezdata.commonlib.core.BaseActivity;
import com.ezdata.commonlib.core.mvp.MvpView;
import com.ezdata.commonlib.widget.TroubleItemDecoration;
import com.ezdata.washtakephoto.R;
import com.ezdata.washtakephoto.adapter.ClothIdListAdapter;
import com.ezdata.washtakephoto.bean.WashTask;
import com.ezdata.washtakephoto.bean.WashTaskDetail;
import com.ezdata.washtakephoto.presenter.WashPresenter;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

public class TaskDetailsActivity extends BaseActivity implements MvpView {

    TextView machineIdTv;
    TextView completeNumTv;
    TextView needNumTv;
    TextView clothGroupTv;
    TextView groupNumTv;
    //TextView groupDetailTv;
    Button takePhotoBtn;

    LRecyclerView listClothIdRv;

    private WashTask task;

    private WashPresenter presenter;
    private final int GET_TASK_CODE = 90;
    //private final int TAKE_PHOTO_CODE = 91;
    private List<WashTaskDetail.clothDetail> clothes = new ArrayList<>();//衣服id组合
    //private List<WashTaskDetail> washTaskDetailsList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_details;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        machineIdTv = findView(R.id.machineIdTv);
        completeNumTv = findView(R.id.completeNumTv);
        needNumTv = findView(R.id.needNumTv);
        clothGroupTv = findView(R.id.clothGroupTv);
        groupNumTv = findView(R.id.groupNumTv);
        // groupDetailTv = findView(R.id.groupDetailTv);
        takePhotoBtn = findViewById(R.id.takePhotoBtn);
        listClothIdRv = findViewById(R.id.listClothIdRv);

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }
    private final static  int TAKE_PHOTO_CODE = 87;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("xcq", "onActivityResult: " + requestCode + "--" + resultCode);
        switch (requestCode){
            case TAKE_PHOTO_CODE:
                if(resultCode==94){
                    getTaskById(task.taskId);
                }

                break;
        }
    }

    @Override
    protected void initListeners() {
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
//                startTakePhoto();
//                takePhotoBtn.setText("正在拍摄中……");
                //takePhotoBtn.setEnabled(false);

                showToast("开始拍摄……");
                //打开新的页面，标注负载量
                Intent it = new Intent(TaskDetailsActivity.this, PhotoInfoActivity.class);
                it.putExtra("taskId",task.taskId);
                startActivityForResult(it, TAKE_PHOTO_CODE);

            }
        });
        adapter.setOnItemClickListener(new ClothIdListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int positon) {
                Intent it = new Intent(TaskDetailsActivity.this, PhotoPreviewActivity.class);
                StringBuilder imgPath = new StringBuilder().append(Constant.BASE_URL).append(clothes.get(positon).path);
                Log.d("xcq", "onItemClick: "+imgPath);
                it.putExtra("imgPath",imgPath.toString());
                startActivity(it);
            }
        });
    }

    private ClothIdListAdapter adapter;
    LRecyclerViewAdapter lrvAdapter;

    @Override
    protected void initData() {
        setTitle("采集");
        task = (WashTask) getIntent().getSerializableExtra("washTask");//获取从列表页传递过来的对象
        if (task != null) {
            machineIdTv.setText(task.machine_code + "号洗衣机");
//            completeNumTv.setText(String.valueOf(task.take_pic_number));
//            needNumTv.setText(task.targetNum + "");
            clothGroupTv.setText(task.classify_names);
            groupNumTv.setText(task.number_desc);


            //设置adapter
            //clothCodes = task.detail.codes;
            adapter = new ClothIdListAdapter(this, clothes);
            //adapter = new ClothIdListAdapter(washTaskDetailsList,this);
            lrvAdapter = new LRecyclerViewAdapter(adapter);
            listClothIdRv.setLayoutManager(new GridLayoutManager(this, 4));
            listClothIdRv.addItemDecoration(new TroubleItemDecoration());
            listClothIdRv.setAdapter(lrvAdapter);

            //初始化LRecyclerView的list
            //refreshLayout.setPtrHandler(mPtrHandler);
            listClothIdRv.setHasFixedSize(true);
            listClothIdRv.setFooterViewHint(getString(R.string.list_loading), getString(R.string.list_nomore), getString(R.string.list_neterror));
            listClothIdRv.setFooterViewColor(R.color.blue, R.color.gray_DC, R.color.white_f0f0f0);
            //listPersonRv.setFooterViewColor(R.color.colorLightRed, R.color.blue2_block, R.color.colorBlack);
            listClothIdRv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);


            //实例化presenter
            presenter = new WashPresenter();
            presenter.attachView(this);
            //TODO
            //getTaskById("009b77d5e4f043d9af0d8eea3c276200");
            getTaskById(task.taskId);
        }
    }

    /**
     * 定时访问这个接口
     *
     * @param taskId
     */
    private void getTaskById(String taskId) {
        presenter.getTaskDetails(taskId, GET_TASK_CODE);

    }

    /**
     * 开始拍照
     */
//    private void startTakePhoto() {
//        presenter.takePhotoV2(task.taskId, TAKE_PHOTO_CODE);
//
//    }

    @Override
    public void onStartRequest(int requestCode) {

    }

    @Override
    public void onSuccess(int requestCode, Object o) {
        switch (requestCode) {
            case GET_TASK_CODE:
                //把衣服列表按照顺序排列显示
                List<WashTaskDetail> temp = (List<WashTaskDetail>) o;
                clothes.clear();
                for (int i = 0; i < temp.size(); i++) {
                    //clothCodes.addAll(temp.get(i).codes);
                    clothes.addAll(temp.get(i).detailLists);
                }
                lrvAdapter.notifyDataSetChanged();

                completeNumTv.setText(String.valueOf(temp.get(0).enterDbNumber));
                needNumTv.setText(temp.get(0).needTaskNumber + "");
                break;
//            case TAKE_PHOTO_CODE:
//
//
//                break;
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
}

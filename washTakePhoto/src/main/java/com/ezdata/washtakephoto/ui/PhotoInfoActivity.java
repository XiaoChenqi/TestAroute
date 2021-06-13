package com.ezdata.washtakephoto.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ezdata.commonlib.Constants.Constant;
import com.ezdata.commonlib.core.BaseActivity;
import com.ezdata.commonlib.glide.GlideUtils;
import com.ezdata.commonlib.utils.ToastUtils;
import com.ezdata.commonlib.widget.TroubleItemDecoration;
import com.ezdata.washtakephoto.R;
import com.ezdata.washtakephoto.adapter.ClothListAdapter;
import com.ezdata.washtakephoto.bean.PhotoDetail;
import com.ezdata.washtakephoto.presenter.PhotoInfoPresenter;
import com.ezdata.washtakephoto.presenter.iview.WashMvpView;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

public class PhotoInfoActivity extends BaseActivity implements WashMvpView {

    Button submitBtn;
    Button deleteBtn;
    RadioGroup rg;
    RadioButton emptyRb;
    RadioButton littleRb;
    RadioButton mediumRb;
    RadioButton maxRb;
    ImageView photoIv;

    public ClothListAdapter adapter;
    LRecyclerViewAdapter lrvAdapter;
    LRecyclerView listClothRv;

    private PhotoInfoPresenter presenter;

    private List<PhotoDetail.ClothGroup> clothes = new ArrayList<>();//衣服组合

    private PhotoDetail toUpdatePhoto;//待上传对象
    private String taskId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        submitBtn = findViewById(R.id.submitBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        emptyRb = findViewById(R.id.emptyRb);
        littleRb = findViewById(R.id.littleRb);
        mediumRb = findViewById(R.id.mediumRb);
        maxRb = findViewById(R.id.maxRb);
        rg = findViewById(R.id.rg);
        listClothRv = findViewById(R.id.listClothRv);
        photoIv = findViewById(R.id.photoIv);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        photoIv.setOnClickListener(ocl);
        submitBtn.setOnClickListener(ocl);
        deleteBtn.setOnClickListener(ocl);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.emptyRb:
                        toUpdatePhoto.setBurden("empty");
                        break;
                    case R.id.littleRb:
                        toUpdatePhoto.setBurden("few");
                        break;
                    case R.id.mediumRb:
                        toUpdatePhoto.setBurden("mid");
                        break;
                    case R.id.maxRb:
                        toUpdatePhoto.setBurden("many");
                        break;
                }
            }
        });
        adapter.setOnNumChangeListener(new ClothListAdapter.OnNumChangeListener() {
            @Override
            public void onNumChange(View v, int position,int num) {
                //每次点击，修改cloth集合的值
                toUpdatePhoto.getClothes().get(position).setNum(num);
            }
        });
    }

    @Override
    protected void initData() {

        setTitle("负载量标注");


        //设置adapter
        adapter = new ClothListAdapter(this,clothes);
        lrvAdapter = new LRecyclerViewAdapter(adapter);
        listClothRv.setLayoutManager(new LinearLayoutManager(this));
        listClothRv.addItemDecoration(new TroubleItemDecoration());
        listClothRv.setAdapter(lrvAdapter);

        //初始化LRecyclerView的list
        //refreshLayout.setPtrHandler(mPtrHandler);
        listClothRv.setHasFixedSize(true);
        listClothRv.setFooterViewHint(getString(R.string.list_loading), getString(R.string.list_nomore), getString(R.string.list_neterror));
        listClothRv.setFooterViewColor(R.color.blue, R.color.gray_DC, R.color.white_f0f0f0);
        //listPersonRv.setFooterViewColor(R.color.colorLightRed, R.color.blue2_block, R.color.colorBlack);
        listClothRv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);


        //实例化presenter
        presenter = new PhotoInfoPresenter();
        presenter.attachView(this);
        taskId = getIntent().getStringExtra("taskId");
        getPhotoInfo(taskId);
    }

    private final int TAKE_PHOTO_CODE = 91;
    private final int SUBMIT_PHOTO_CODE = 92;
    private final int DELETE_PHOTO_CODE = 93;
    private final int REFRESH_LIST_CODE = 94;

    private void getPhotoInfo(String taskId) {
        Log.d("xcq", "getPhotoInfo: " + taskId);
        presenter.takePhotoV2(taskId, TAKE_PHOTO_CODE);
    }

    @Override
    public void onStartRequest(int requestCode) {

    }

    @Override
    public void onSuccess(int requestCode, Object o) {

        switch (requestCode){
            case TAKE_PHOTO_CODE:
                //把衣服列表按照顺序排列显示
                PhotoDetail temp = (PhotoDetail) o;
                StringBuilder imgPath = new StringBuilder().append(Constant.BASE_URL).append(temp.picUrl);
                GlideUtils.displayUrl(photoIv, imgPath.toString(), R.drawable.loading_01);

                clothes.clear();
                for (int i = 0; i < temp.getClothes().size(); i++) {
                    clothes.add(temp.getClothes().get(i));

                }
                lrvAdapter.notifyDataSetChanged();

                toUpdatePhoto = temp;
                break;
            case SUBMIT_PHOTO_CODE:
                ToastUtils.show(this, "照片提交成功", Toast.LENGTH_LONG);
                // 关闭这个页面
                Intent intent = new Intent();
                setResult(REFRESH_LIST_CODE, intent);
                finish();
                break;
            case DELETE_PHOTO_CODE:
                ToastUtils.show(this, "照片作废成功", Toast.LENGTH_LONG);
                // 关闭这个页面
                finish();
                break;
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

    private View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.submitBtn:
                    submitBurden();
                    //submitBtn.setEnabled(false);
                    break;
                case R.id.deleteBtn:
                    deletePhoto();
                    //deleteBtn.setEnabled(false);
                    break;
                case R.id.photoIv:
                    Intent it = new Intent(PhotoInfoActivity.this, PhotoPreviewActivity.class);
                    StringBuilder imgPath = new StringBuilder().append(Constant.BASE_URL).append(toUpdatePhoto.picUrl);
                    it.putExtra("imgPath",imgPath.toString());
                    startActivity(it);
                    break;
            }
        }
    };

    /**
     * 提交参数
     */
    private void submitBurden() {

        /*
        skirt:3-fleece:1
        */
        StringBuilder classifys = new StringBuilder();
        for(int i=0;i<toUpdatePhoto.getClothes().size();i++){
            PhotoDetail.ClothGroup clothGroup = toUpdatePhoto.getClothes().get(i);
            classifys.append(clothGroup.getLabel()).append(":").append(clothGroup.getNum()).append("-");
        }
        presenter.submitPhotoInfo(toUpdatePhoto.getPicUrl(),
                toUpdatePhoto.getBurden(),
                classifys.toString(),
                taskId, SUBMIT_PHOTO_CODE);
    }

    /**
     * 删除照片
     */
    private void deletePhoto() {

        presenter.deletPhoto(toUpdatePhoto.getPicUrl(), DELETE_PHOTO_CODE);
    }
    @Override
    public void burdenNull() {
        submitBtn.setEnabled(true);
        ToastUtils.show(this, "负载量不能为空", Toast.LENGTH_LONG);

    }
}

package com.ezdata.commonlib.core;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.ezdata.commonlib.R;

//import butterknife.Bind;


/**
 * Created by MSI-PC on 2018/5/25.
 */

public abstract class BaseToolBarActivity extends BaseActivity {

    //@Bind(R.id.toolBar)
    Toolbar mToolbar;
    //@Bind(R.id.titleTv)
    TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //此处实例化布局
        super.onCreate(savedInstanceState);
        mToolbar = findViewById(R.id.toolBar);
        titleTv = findViewById(R.id.titleTv);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        this.initToolbarHelper();
    }

    /**
     * init the toolbar
     */
    protected void initToolbarHelper() {
        if (this.mToolbar == null) {
            this.mToolbar=(Toolbar)findViewById(R.id.toolBar);
        }
        if(mToolbar==null)
            return;
        this.setSupportActionBar(this.mToolbar);
    }
    public void setTitle(String title){
        if(mToolbar!=null)
            mToolbar.setTitle("");
        if(titleTv!=null)
            titleTv.setText(title);

    }
    public void setTitle(int id){
        setTitle(getString(id));
    }
    protected void showBack() {

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onToolbarBack();
            }
        });
    }
    protected abstract void onToolbarBack();
}

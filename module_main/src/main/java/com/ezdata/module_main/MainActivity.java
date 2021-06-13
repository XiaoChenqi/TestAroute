package com.ezdata.module_main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

//import butterknife.Bind;

public class MainActivity extends AppCompatActivity {

    //@Bind(R.id.enterTv)
    TextView enterTv;
    TextView prisonTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterTv = findViewById(R.id.enterTv);
        prisonTv = findViewById(R.id.prisonTv);
        enterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/washTakePhoto/main").navigation();
            }
        });
        prisonTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/prison/main").navigation();
            }
        });
    }
}

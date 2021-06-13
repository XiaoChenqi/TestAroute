package com.ezdata.washtakephoto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ezdata.commonlib.Constants.Constant;
import com.ezdata.washtakephoto.ui.TaskListActivity;
import com.ezdata.washtakephoto.ui.ZxingCameraActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

@Route(path = "/washTakePhoto/main")
public class MainActivity extends AppCompatActivity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tvQrcode;
    EditText urlEt;
    private Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash);
        _context = MainActivity.this;
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tvQrcode = findViewById(R.id.tv6);
        urlEt  = findViewById(R.id.urlEt);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(_context, TaskListActivity.class);
                it.putExtra(Constant.MACHINE_ID, "0");
                Constant.setBaseUrl(urlEt.getText().toString());
                startActivity(it);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(_context, TaskListActivity.class);
                it.putExtra(Constant.MACHINE_ID, "1");
                Constant.setBaseUrl(urlEt.getText().toString());
                startActivity(it);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(_context, TaskListActivity.class);
                it.putExtra(Constant.MACHINE_ID, "2");
                Constant.setBaseUrl(urlEt.getText().toString());
                startActivity(it);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(_context, TaskListActivity.class);
                it.putExtra(Constant.MACHINE_ID, "3");
                Constant.setBaseUrl(urlEt.getText().toString());
                startActivity(it);
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(_context, TaskListActivity.class);
                it.putExtra(Constant.MACHINE_ID, "4");
                Constant.setBaseUrl(urlEt.getText().toString());
                startActivity(it);

            }
        });
        urlEt.setText(Constant.BASE_URL);

        tvQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(_context, ZxingCameraActivity.class);
                Constant.setBaseUrl(urlEt.getText().toString());
                startActivity(it);
                //new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });
    }

    String TAG = "zhouyang";
    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}

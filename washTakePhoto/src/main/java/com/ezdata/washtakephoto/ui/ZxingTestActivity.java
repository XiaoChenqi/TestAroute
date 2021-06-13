package com.ezdata.washtakephoto.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;

import com.ezdata.washtakephoto.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class ZxingTestActivity extends AppCompatActivity {

    private DecoratedBarcodeView barcodeView;
    private CaptureManager capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_test);



        new IntentIntegrator(this).initiateScan();

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(ZxingTestActivity.class);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();

        barcodeView =findViewById(R.id.zxing_barcode_scanner);

        capture = new CaptureManager(this, barcodeView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();


    }
//String TAG = "zhouyang";
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,  data);
//        Log.d(TAG, "onActivityResult: 1");
//        if(result != null) {
//            if(result.getContents() == null) {
//                Log.d(TAG, "onActivityResult: 2");
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//
//            } else {
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//                Log.d(TAG, "Scanned: "+ result.getContents());
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//
//    }


    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}

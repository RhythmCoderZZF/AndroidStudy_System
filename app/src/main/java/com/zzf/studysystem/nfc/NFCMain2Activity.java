package com.zzf.studysystem.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import com.rhythmcoderzzf.baselib.BaseActivity;
import com.rhythmcoderzzf.baselib.utils.LogUtil;
import com.zzf.studysystem.R;

public class NFCMain2Activity extends BaseActivity {

    private TextView mTvTagRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nfc);
        mTvTagRes = findViewById(R.id.tvTagRes);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.d(TAG, "onNewIntent<<");
    }
}
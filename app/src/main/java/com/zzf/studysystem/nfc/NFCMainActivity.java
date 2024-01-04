package com.zzf.studysystem.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import com.rhythmcoderzzf.baselib.BaseActivity;
import com.rhythmcoderzzf.baselib.utils.LogUtil;
import com.zzf.studysystem.R;

public class NFCMainActivity extends BaseActivity {
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mIntentFilters;
    private String[][] mTechList;
    private TextView mTvTagRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nfc);
        mTvTagRes = findViewById(R.id.tvTagRes);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcCheck()) {
            //NFC前台调度系统，允许最前台的Activity拦截Intent
            mAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, mTechList);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcCheck()) mAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.d(TAG, "onNewIntent<<");
        try {
            final Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            mTvTagRes.setText(tag.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean nfcCheck() {
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            toast("device do not support NFC!");
            return false;
        } else {
            if (!mAdapter.isEnabled()) {
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                return false;
            }
        }
        LogUtil.d(TAG, "nfcCheck<< check ok...");
        return true;
    }
}
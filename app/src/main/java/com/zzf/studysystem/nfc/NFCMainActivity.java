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

public class NFCMainActivity extends BaseActivity {
    private NfcAdapter mAdapter;
    private IntentFilter[] mIntentFilters;
    private PendingIntent mPendingIntent;
    private String[][] mTechList;

    private TextView mTvTagRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nfc);
        mTvTagRes = findViewById(R.id.tvTagRes);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE);
        IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter filte2 = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter filte3 = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        /*try {
            filter.addDataType("text/plain");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException(e);
        }*/
        mIntentFilters = new IntentFilter[]{filter, filte2, filte3};
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcCheck())
            mAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, mTechList);
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
            Toast.makeText(this, "do not support NFC!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (!mAdapter.isEnabled()) {
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                return false;
            }
        }
        LogUtil.d(TAG, "nfcCheck<< check ok");
        return true;
    }
}
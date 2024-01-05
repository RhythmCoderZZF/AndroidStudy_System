package com.zzf.studysystem.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.widget.TextView;

import com.rhythmcoderzzf.baselib.BaseActivity;
import com.rhythmcoderzzf.baselib.utils.LogUtil;
import com.zzf.studysystem.R;

import java.util.Arrays;

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
        if (!nfcCheck()) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, mTechList);
        try {
            String action = getIntent().getAction();
            LogUtil.d(TAG, "action: " + action);
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
                Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(
                        NfcAdapter.EXTRA_NDEF_MESSAGES);

                if (rawMsgs != null && rawMsgs.length > 0) {
                    LogUtil.d(TAG, "ndef tag: " + Arrays.toString(rawMsgs));
                    NdefMessage msg = (NdefMessage) rawMsgs[0];
                    mTvTagRes.setText(new String(msg.getRecords()[0].getPayload()));
                }
            } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action) || NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
                Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
                LogUtil.d(TAG, "tech|tag tag: " + tag);
                NfcA.get(tag);
                mTvTagRes.setText(tag.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
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
        LogUtil.d(TAG, "nfc feature check ok...");
        return true;
    }
}
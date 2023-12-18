package com.rhythmcoderzzf.baselib;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.rhythmcoderzzf.baselib.utils.Constants;

/**
 * Author:create by RhythmCoderZZF
 * Date:2023/12/16
 * Description:
 */
public class BaseActivity extends AppCompatActivity {
    protected static String TAG = "";
    private String mInfo;
    private Dialog mDialogInfo;

    {
        TAG = this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getIntent().getStringExtra(Constants.INTENT_TITLE));
        mInfo = getIntent().getStringExtra(Constants.INTENT_INFO);
        mInfo = mInfo == null ? "" : mInfo;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mInfo.isEmpty()) {
            menu.findItem(R.id.info).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.info) {
            if (mDialogInfo == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                mDialogInfo = builder.setTitle(getString(R.string.info)).setIcon(ContextCompat.getDrawable(this, R.drawable.baseline_info_24)).setMessage(mInfo).setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss()).create();
            }
            mDialogInfo.show();
        } else if (itemId == android.R.id.home) {
            finish();
        }
        return true;
    }
}

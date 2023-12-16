package com.zzf.studysystem._base;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.zzf.studysystem.R;
import com.zzf.studysystem._base.utils.LogUtil;

/**
 * Author:create by RhythmCoder
 * Date:2023/12/16
 * Description:
 */
public class BaseActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName() + "-BaseActivity";
    private String mInfo = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getIntent().getStringExtra("title"));
        mInfo = getIntent().getStringExtra("info");
        mInfo = mInfo == null ? "" : mInfo;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LogUtil.d(TAG, "onCreateOptionsMenu<<");
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        LogUtil.d(TAG, "onPrepareOptionsMenu<<");
        if (mInfo.isEmpty()) {
            menu.findItem(R.id.tips).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.tips) {
        } else if (itemId == android.R.id.home) {
            finish();
        }
        return true;
    }
}

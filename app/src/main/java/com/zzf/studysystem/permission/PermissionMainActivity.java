package com.zzf.studysystem.permission;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.rhythmcoderzzf.baselib.BaseActivity;
import com.zzf.studysystem.R;

public class PermissionMainActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtnRequestPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        mBtnRequestPermission = findViewById(R.id.btnRequestPermission);
        mBtnRequestPermission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //[Module-Permission] #2 check has Permission
        int isGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        Toast.makeText(this, "has contact permission : " + isGranted, Toast.LENGTH_SHORT).show();
    }
}
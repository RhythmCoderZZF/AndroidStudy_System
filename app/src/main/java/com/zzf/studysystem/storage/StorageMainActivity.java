package com.zzf.studysystem.storage;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.rhythmcoderzzf.baselib.BaseActivity;
import com.rhythmcoderzzf.baselib.ListAdapter;
import com.rhythmcoderzzf.baselib.TitleBean;
import com.zzf.studysystem.R;
import com.zzf.studysystem.nfc.NFCMainActivity;
import com.zzf.studysystem.permission.PermissionMainActivity;
import com.zzf.studysystem.usb.UsbMainActivity;

import java.util.ArrayList;
import java.util.List;

public class StorageMainActivity extends BaseActivity {
    private final List<TitleBean> mList = new ArrayList<>();

    private List<TitleBean> initRvDataList() {
        mList.add(new TitleBean(getString(R.string.storage_app_specific_files), "", getString(R.string.storage_app_specific_files_info), UsbMainActivity.class));
        mList.add(new TitleBean(getString(R.string.permission),getString(R.string.permission_sub_title),getString(R.string.permission_info), PermissionMainActivity.class));
        mList.add(new TitleBean("NFC", NFCMainActivity.class));
        return mList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        initSimpleProjectListView(findViewById(R.id.rv),new ListAdapter(initRvDataList()));
    }
}
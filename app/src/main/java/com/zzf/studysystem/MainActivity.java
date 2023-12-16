package com.zzf.studysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.rhythmcoderzzf.baselib.ListAdapter;
import com.rhythmcoderzzf.baselib.TitleBean;
import com.zzf.studysystem.permission.PermissionMainActivity;
import com.zzf.studysystem.usb.UsbMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private final List<TitleBean> mList = new ArrayList<>();

    private void initRvDataList() {
        mList.add(new TitleBean("USB", getString(R.string.usb_subTitle), getString(R.string.usb_info), UsbMainActivity.class));
        mList.add(new TitleBean(getString(R.string.permission), PermissionMainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRvDataList();
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
        ListAdapter adapter = new ListAdapter(mList);
        mRv.setAdapter(adapter);
    }
}
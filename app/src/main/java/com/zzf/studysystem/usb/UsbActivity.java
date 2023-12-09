package com.zzf.studysystem.usb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.widget.Toast;

import com.zzf.studysystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsbActivity extends AppCompatActivity implements OnDeviceConnectListener {
    private UsbManager mUsbManager;
    private USBMonitor mUsbMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb);
        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        mUsbMonitor = new USBMonitor(mUsbManager, this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //do not register UsbMonitor in onRresume() func
        mUsbMonitor.register();

        List<UsbDevice> listDevices = mUsbMonitor.getDeviceList();
        UsbDevice device = listDevices.size() > 0 ? listDevices.get(0) : null;
        if (device != null) {
            mUsbMonitor.requestPermission(device);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUsbMonitor.unRegister();

    }

    @Override
    public void onAttach(UsbDevice device) {
        Toast.makeText(this, "USB onAttach!", Toast.LENGTH_SHORT).show();
        mUsbMonitor.requestPermission(device);
    }

    @Override
    public void onDettach(UsbDevice device) {
        Toast.makeText(this, "USB onDettach!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnect(UsbDevice device) {
        Toast.makeText(this, "USB onConnect!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnect(UsbDevice device) {
        Toast.makeText(this, "USB onDisconnect!", Toast.LENGTH_SHORT).show();
    }
}
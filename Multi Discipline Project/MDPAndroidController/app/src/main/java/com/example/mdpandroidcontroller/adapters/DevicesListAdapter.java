package com.example.mdpandroidcontroller.adapters;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.mdpandroidcontroller.R;

import java.util.ArrayList;
import java.util.List;


public class DevicesListAdapter extends ArrayAdapter<BluetoothDevice> {

    private LayoutInflater layoutInflater;
    private ArrayList<BluetoothDevice> deviceslist = new ArrayList<>();
    private int resourceId;


    public DevicesListAdapter(@NonNull Context context, int resource, ArrayList<BluetoothDevice> deviceslist) {
        super(context, resource, deviceslist);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceId = resource;
        this.deviceslist = deviceslist;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = layoutInflater.inflate(resourceId, null);

        BluetoothDevice bluetoothDevice = deviceslist.get(position);

        if (bluetoothDevice != null) {
            TextView deviceName = (TextView) convertView.findViewById(R.id.tvDeviceName);
            TextView deviceAdd = (TextView) convertView.findViewById(R.id.tvDeviceAddress);

            if (deviceName != null) {
                deviceName.setText(bluetoothDevice.getName());
                deviceAdd.setText(bluetoothDevice.getAddress());
            }
        }

        return convertView;
    }
}

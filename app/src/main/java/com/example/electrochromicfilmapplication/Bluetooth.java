package com.example.electrochromicfilmapplication;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public class Bluetooth extends Activity {
    private final static int REQUEST_ENABLE_BT = 1;
    private OutputStream outputStream;
    private InputStream inputStream;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    //ListView lv;

    public Bluetooth() {
        //Check for Devices Support Of Bluetooth
        BA = BluetoothAdapter.getDefaultAdapter();
        if (BA == null) {
            //Device Doesn't Support Bluetooth
        }
        //Enable Bluetooth
        if(!BA.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
    //Disables the Bluetooth Adapter Which Disables the Connection
    public void off(View v) {
        BA.disable();
        Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_LONG).show();
    }


    public void visible(View v) {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void write(String s) throws IOException {
        outputStream.write(s.getBytes());
    }

    public void run() {
        final int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytes = 0;
        int b = BUFFER_SIZE;

    }














    /*

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // b1 = (Button) findViewById(R.id.button);
        //b2 = (Button) findViewById(R.id.button2);
        //b3 = (Button) findViewById(R.id.button3);
        //b4 = (Button) findViewById(R.id.button4);

        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView) findViewById(R.id.listView);
    }

    public void on(View v) {
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
        }
    }




    public void list(View v) {
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();

        for (BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);
    }

     */
}








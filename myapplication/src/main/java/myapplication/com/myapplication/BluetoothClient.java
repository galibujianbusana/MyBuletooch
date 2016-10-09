package myapplication.com.myapplication;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2016/9/23.
 */
public class BluetoothClient {

    BluetoothDevice device = null;

    //通过构造函数来传入一个BluetoothDevice实例
    public BluetoothClient(BluetoothDevice device) {
        this.device = device;
    }
    BluetoothSocket socket = null;
    void connetServer() throws IOException {
        Thread _clientThread = new Thread(new Runnable() {
            public void run() {
                try {
                    //通过BluetoothDevice实例的createRfcommSocketToServiceRecord方法可以返回一个带有UUID的BluetoothSocket实例
                    socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    socket.connect();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        });
        _clientThread.start();
    }
}

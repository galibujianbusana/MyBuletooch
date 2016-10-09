package myapplication.com.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncStatusObserver;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter adapter;
    ArrayList<String> datas=new ArrayList<String>();
    ListView listv;
    ArrayAdapter<String> ad;
    BluetoothDevice remoteD;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "00:15:83:00:67:AB"; // <==要连接的蓝牙设备MAC地址
    Button btn;
    BluetoothSocket clienSocket, socket;
    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initView();
        }



    private void initView() {
        listv= (ListView) findViewById(R.id.listView);
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bl = datas.get(position);
                //使用"-"分割字符串
                String[] values = bl.split("-");
                Toast.makeText(MainActivity.this,""+values[values.length-1],Toast.LENGTH_SHORT).show();
               // connect(values[values.length-1]);
                 connectDevice();
            }
        });
        ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listv.setAdapter(ad);

    }
    //连接到指定的设备
    private void connect(String value) {
        //远程设备
        remoteD = adapter.getRemoteDevice(value);
        //使用反射获取指定类中所有的方法
        Method[] methods = BluetoothDevice.class.getMethods();
        for(Method m: methods){
            System.out.println("--"+m.getName());
        }
        //获取指定的方法
        try {
         //   Method bond = BluetoothDevice.class.getMethod("createBond");
            //调用该方法
          //  bond.invoke(remoteD);
            System.out.println("--success");
        } catch (Exception e) {
            e.printStackTrace();

        }
        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);
        BluetoothSocket socket = null;
        try {
            socket = remoteD.createRfcommSocketToServiceRecord(uuid);
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    //解除配对
    public void delete(View v){
        try {
            Method removeB = BluetoothDevice.class.getMethod("removeBond");
            removeB.invoke(remoteD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化
    private void init() {
        IntentFilter filter=new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        //动态注册广播接收器
        this.registerReceiver(new A(),filter);
    }

    //开启蓝牙
    public void enable(View v){
        //蓝牙设备
        adapter = BluetoothAdapter.getDefaultAdapter();
        //启用
        adapter.enable();
    }
    //扫描周边设备
    public void scan(View v){
        /*
        action,extra,data,type,flag,category,component
         */
        //指定为请求扫描action
        adapter=BluetoothAdapter.getDefaultAdapter();
        Intent it=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //指定扫描的时长
        it.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,6000);
        startActivity(it);
        //开始扫描,每当扫到一个蓝牙设备就发送一条广播
        adapter.startDiscovery();

    }
    public void close(View v){
        try{
            //关闭禁用
            adapter.disable();
        }catch (Exception e){}

    }
    class A extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)){
                //获取扫描到的设备
                BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                String address = device.getAddress();
                System.out.println("--扫到的设备:"+name+":"+address);
                //数据变化了
                datas.add(name+"-"+address);
                //刷新列表
                ad.notifyDataSetChanged();
            }
        }
    }
    //使用系统功能发送文件
    public void send(View v){
//        //action:为发送操作
//        Intent it=new Intent(Intent.ACTION_SEND);
//        it.setType("*/*");
//        File f=new File("/sdcard/x.txt");
//        //发送的文件
//        it.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
//        //使用系统发送
//        PackageManager pm = getPackageManager();
//        //获取所有可以发送文件的app
//        List<ResolveInfo> infos = pm.queryIntentActivities(it, 0);
//        for (ResolveInfo info:infos){
//            String p = info.activityInfo.packageName;
//            if (p.equals("com.android.bluetooth")){
//                String name = info.activityInfo.name;
//                it.setComponent(new ComponentName(p,name));
//                break;
//            }
//        }
//        startActivity(it);
        ///////////////////////////



//            try {
//                OutputStream os = MainActivity.socket.getOutputStream(); // 蓝牙连接输出流
//                os.write(cmd);
//            } catch (IOException e) {
//            }

    }


    public  void connectDevice(){
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.isEnabled();

        Toast.makeText(MainActivity.this,"正在尝试连接蓝牙设备，请稍后····",Toast.LENGTH_SHORT).show();
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        try {
            socket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            Toast.makeText(MainActivity.this,"套接字创建失败，请稍后····",Toast.LENGTH_SHORT).show();

            //bluetoothFlag = false;
        }

        mBluetoothAdapter.cancelDiscovery();
        try {
            socket.connect();
            Toast.makeText(MainActivity.this,"连接成功建立，可以开始操控了!，请稍后····",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            try {
                socket.close();

            } catch (IOException e2) {
                Toast.makeText(MainActivity.this,"连接没有建立，无法关闭套接字，可以开始操控了!，请稍后····",Toast.LENGTH_SHORT).show();

            }
        }

        if(true){
            try {
               InputStream inStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            } //绑定读接口

            try {
               OutputStream outStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            } //绑定写接口

        }
    }
}

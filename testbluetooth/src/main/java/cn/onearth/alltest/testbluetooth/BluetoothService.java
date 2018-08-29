package cn.onearth.alltest.testbluetooth;


import org.bluez.v3.Adapter;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/7/21
 */
public class BluetoothService implements Runnable {

    private static volatile boolean stopFlag = false;

    private static LocalDevice localDevice;

    // 流连接
    private static StreamConnection streamConnection = null;
    // 接受数据的字节流
    private static byte[] acceptdByteArray = new byte[1024];
    // 输入流
    private static DataInputStream inputStream;
    //接入通知
    private static StreamConnectionNotifier notifier;
    //线程池
    private final static ExecutorService service = Executors.newCachedThreadPool();


    public BluetoothService() {


        try {
            localDevice = LocalDevice.getLocalDevice();
            if (!localDevice.setDiscoverable(DiscoveryAgent.GIAC)) {

                System.out.println("请将蓝牙设置为可被发现");
            }
            /**
             * 作为服务端，被请求
             */
            String url = "btspp://localhost:" + "2D26618601FB47C28D9F10B8EC891363"
                    + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        service.submit(this);
    }


    public void run() {
        try {
            String inStr = null;
            //阻塞的，等待设备连接
            streamConnection = notifier.acceptAndOpen();
            inputStream = streamConnection.openDataInputStream();
            int length;
            while (true) {
                //不阻塞线程
                if ((inputStream.available()) <= 0) {
                    //UI停止后，关闭
                    if (stopFlag) {
                        break;
                    }
                    //数据间隔比较长，手动堵塞线程
                    Thread.sleep(800);
                } else {
                    length = inputStream.read(acceptdByteArray);
                    if (length > 0) {
                        inStr = new String(acceptdByteArray, 0, length);
                        System.out.println(inStr);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (streamConnection != null) {
                    streamConnection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {

        BluetoothService bluetoothService = new BluetoothService();
        Thread.sleep(99999);
    }
}

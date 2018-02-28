package tuannm.com.chatdemo;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by TNM on 2/17/2018.
 */

public class App extends Application {
    public static Socket mSocket;

    public App(){
        connectToServer();
    }

    private void connectToServer() {
        try {
            mSocket = IO.socket("http://192.168.26.2:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
    }
}

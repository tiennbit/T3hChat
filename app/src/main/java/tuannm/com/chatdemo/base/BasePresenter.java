package tuannm.com.chatdemo.base;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by TNM on 2/12/2018.
 */

public abstract class BasePresenter {
    protected Socket socket;

    public BasePresenter(){
        connectToServer();
    }

    private void connectToServer(){
        try {
            socket = IO.socket("http://192.168.136.2:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.connect();
    }
}

package tuannm.com.chatdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import tuannm.com.chatdemo.App;
import tuannm.com.chatdemo.view.activity.MainActivity;

/**
 * Created by TNM on 2/16/2018.
 */

public class DemoService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        App.mSocket.on("server-send-request-friend", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject object = (JSONObject) args[0];
                try {
                    String name = object.getString("username");
                    Log.d("OOOOOOOOOOOOOOOOOOOOOO", "NAMEEEEEEEEEEEEEEEEEEEEE: " + name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return START_REDELIVER_INTENT;
    }
}

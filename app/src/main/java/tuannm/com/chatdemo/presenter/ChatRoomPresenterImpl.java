package tuannm.com.chatdemo.presenter;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.emitter.Emitter;
import tuannm.com.chatdemo.App;
import tuannm.com.chatdemo.dto.MessageDto;
import tuannm.com.chatdemo.view.fragment.RoomChatView;

/**
 * Created by TNM on 2/18/2018.
 */

public class ChatRoomPresenterImpl implements ChatRoomPresenter {
    private RoomChatView roomChatView;

    public ChatRoomPresenterImpl(RoomChatView roomChatView) {
        this.roomChatView = roomChatView;
    }

    @Override
    public void sendMessage(String message) {
        App.mSocket.emit("client-send-message", message);
    }

    @Override
    public void receiveMessage() {
        App.mSocket.on("server-send-message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject object = (JSONObject) args[0];
                try {
                    String sender = object.getString("sender");
                    String message = object.getString("message");
                    MessageDto messageDto = new MessageDto(sender, message);
                    roomChatView.receiveMessa(messageDto);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

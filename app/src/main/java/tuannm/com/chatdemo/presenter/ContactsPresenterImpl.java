package tuannm.com.chatdemo.presenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.emitter.Emitter;
import tuannm.com.chatdemo.App;
import tuannm.com.chatdemo.dto.UserDto;
import tuannm.com.chatdemo.view.fragment.ContactsView;

/**
 * Created by TNM on 2/23/2018.
 */

public class ContactsPresenterImpl implements ContactsPresenter {
    private ContactsView view;

    public ContactsPresenterImpl(ContactsView view) {
        this.view = view;
    }

    @Override
    public void getUsers() {
        App.mSocket.emit("client-get-users", "");
        App.mSocket.on("server-send-users", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                List<UserDto> userDtos = new ArrayList<>();
                JSONObject object = (JSONObject) args[0];
                try {
                    JSONArray array = object.getJSONArray("users");
                    for(int i = 0; i < array.length(); i++){
                        JSONObject user = (JSONObject) array.get(i);
                        String username = user.getString("username");
                        byte[] bytes = (byte[]) user.get("image");
                        UserDto userDto = new UserDto(username, bytes);
                        userDtos.add(userDto);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                view.getUser(userDtos);
            }
        });
    }
}

package tuannm.com.chatdemo.presenter;

import tuannm.com.chatdemo.dto.MessageDto;

/**
 * Created by TNM on 2/18/2018.
 */

public interface ChatRoomPresenter {
    void sendMessage(String message);
    void receiveMessage();
}

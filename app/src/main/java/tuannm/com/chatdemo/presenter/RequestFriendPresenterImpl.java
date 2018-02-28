package tuannm.com.chatdemo.presenter;

import tuannm.com.chatdemo.App;
import tuannm.com.chatdemo.base.BasePresenter;
import tuannm.com.chatdemo.view.fragment.ResultSearchFriendView;

/**
 * Created by TNM on 2/14/2018.
 */

public class RequestFriendPresenterImpl implements RequestFriendPresenter {
    ResultSearchFriendView resultSearchFriendView;

    public RequestFriendPresenterImpl(ResultSearchFriendView resultSearchFriendView) {
        this.resultSearchFriendView = resultSearchFriendView;
    }

    @Override
    public void requestFriend(String username) {
        App.mSocket.emit("client-send-request-friend", username);
    }
}

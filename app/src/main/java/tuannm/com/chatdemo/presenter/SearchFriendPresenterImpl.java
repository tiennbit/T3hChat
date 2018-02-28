package tuannm.com.chatdemo.presenter;

import io.socket.emitter.Emitter;
import tuannm.com.chatdemo.App;
import tuannm.com.chatdemo.base.BasePresenter;
import tuannm.com.chatdemo.view.fragment.SearchFriendView;

/**
 * Created by TNM on 2/14/2018.
 */

public class SearchFriendPresenterImpl implements SearchFriendPresenter{
    private SearchFriendView searchFriendView;

    public SearchFriendPresenterImpl(SearchFriendView searchFriendView) {
        this.searchFriendView = searchFriendView;
    }

    @Override
    public void searchUser(final String username) {
        //B1: gui username len server
        App.mSocket.emit("client-find-user", username);
        //B2. nhat ket qua tra ve:
        App.mSocket.on("server-has-user", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                searchFriendView.searchHasResult(username);
            }
        });

        App.mSocket.on("server-has-not-user", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                searchFriendView.searchHasNotResult();
            }
        });
    }
}

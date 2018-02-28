package tuannm.com.chatdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.base.BaseFragment;
import tuannm.com.chatdemo.presenter.SearchFriendPresenterImpl;

/**
 * Created by TNM on 2/12/2018.
 */

public class SearchFriendFragment extends BaseFragment implements SearchFriendView, View.OnClickListener {
    private EditText edtUsername;
    private Button btnSearch;
    private OnSearchFriendFragmentListeners listeners;

    private SearchFriendPresenterImpl searchFriendPresenter;

    public void setListeners(OnSearchFriendFragmentListeners listeners) {
        this.listeners = listeners;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_search_friend;
    }

    @Override
    public void initializeComponents() {
        edtUsername = rootView.findViewById(R.id.edt_username);
        btnSearch = rootView.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchFriendPresenter = new SearchFriendPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                searchFriendPresenter.searchUser(edtUsername.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void searchHasResult(String username) {
        listeners.showResultSearchFriend(username);
    }

    @Override
    public void searchHasNotResult() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Tài khoản này không tồn tại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnSearchFriendFragmentListeners{
        void showResultSearchFriend(String username);
    }
}

package tuannm.com.chatdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.base.BaseFragment;
import tuannm.com.chatdemo.presenter.RequestFriendPresenterImpl;
import tuannm.com.chatdemo.view.activity.MainActivity;

/**
 * Created by TNM on 2/12/2018.
 */

public class ResultSearchFriendFragment extends BaseFragment implements View.OnClickListener, ResultSearchFriendView{
    private CircleImageView imgAvatar;
    private TextView tvUsername;
    private Button btnMakeFriend;

    private RequestFriendPresenterImpl requestFriendPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestFriendPresenter = new RequestFriendPresenterImpl(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_result_search_friend;
    }

    @Override
    public void initializeComponents() {
        imgAvatar = rootView.findViewById(R.id.img_avatar);
        tvUsername = rootView.findViewById(R.id.txt_username);
        tvUsername.setText(((MainActivity) getActivity()).getUsername());
        btnMakeFriend = rootView.findViewById(R.id.btn_make_friend);
        btnMakeFriend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_make_friend:
                requestFriendPresenter.requestFriend(tvUsername.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void showNotification() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Sent request make friend!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

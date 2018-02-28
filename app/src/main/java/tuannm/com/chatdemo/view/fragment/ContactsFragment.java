package tuannm.com.chatdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.base.BaseFragment;
import tuannm.com.chatdemo.dto.UserDto;
import tuannm.com.chatdemo.presenter.ContactsPresenterImpl;
import tuannm.com.chatdemo.view.adapter.UserAdapter;

/**
 * Created by TNM on 2/12/2018.
 */

public class ContactsFragment extends BaseFragment implements View.OnClickListener, ContactsView {
    private RecyclerView rcvContacts;
    private UserAdapter userAdapter;
    private List<UserDto> userDtos;

    private ContactsPresenterImpl contactsPresenter;

    private ImageView btnShowSearchFriend;
    private OnContactsFragmentListeners listeners;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactsPresenter = new ContactsPresenterImpl(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_contacts;
    }

    public void setListeners(OnContactsFragmentListeners listeners) {
        this.listeners = listeners;
    }

    @Override
    public void initializeComponents() {
        btnShowSearchFriend = rootView.findViewById(R.id.btn_show_search_friend);
        btnShowSearchFriend.setOnClickListener(this);
        rcvContacts = rootView.findViewById(R.id.rcv_contacts);
        contactsPresenter.getUsers();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_search_friend:
                listeners.showSearchFriend();
                break;
            default:
                break;
        }
    }

    @Override
    public void getUser(List<UserDto> users) {
        userDtos = users;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userAdapter = new UserAdapter(getContext(), userDtos);
                rcvContacts.setAdapter(userAdapter);
            }
        });
    }

    public interface OnContactsFragmentListeners {
        void showSearchFriend();
    }
}

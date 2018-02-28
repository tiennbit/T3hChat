package tuannm.com.chatdemo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.emitter.Emitter;
import tuannm.com.chatdemo.App;
import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.base.BaseActivity;
import tuannm.com.chatdemo.service.DemoService;
import tuannm.com.chatdemo.view.fragment.ContactsFragment;
import tuannm.com.chatdemo.view.fragment.MessagesFragment;
import tuannm.com.chatdemo.view.fragment.ResultSearchFriendFragment;
import tuannm.com.chatdemo.view.fragment.RoomChatFragment;
import tuannm.com.chatdemo.view.fragment.SearchFriendFragment;

/**
 * Created by TNM on 2/12/2018.
 */

public class MainActivity extends BaseActivity implements ContactsFragment.OnContactsFragmentListeners, SearchFriendFragment.OnSearchFriendFragmentListeners, View.OnClickListener {
    private MessagesFragment messagesFragment;
    private ContactsFragment contactsFragment;
    private SearchFriendFragment searchFriendFragment;
    private ResultSearchFriendFragment resultSearchFriendFragment;
    private RoomChatFragment roomChatFragment;

    private ImageView btnShowMessages;
    private ImageView btnShowContacts;
    private ImageView btnChatRoom;

    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startDemoService();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeComponents() {
        btnShowMessages = findViewById(R.id.btn_show_messages);
        btnShowContacts = findViewById(R.id.btn_show_contacts);
        btnChatRoom = findViewById(R.id.btn_show_room);

        btnShowMessages.setOnClickListener(this);
        btnShowContacts.setOnClickListener(this);
        btnChatRoom.setOnClickListener(this);
        showContactsFragment();
    }

    private void startDemoService() {
        Intent intent = new Intent(this, DemoService.class);
        startService(intent);
        Log.d("", "startDemoService: Da Bat Service");
    }

    private void showContactsFragment() {
        if (contactsFragment == null) {
            contactsFragment = new ContactsFragment();
            contactsFragment.setListeners(this);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, contactsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showSearchFriend() {
        showSearchFriendFragment();
    }

    private void showSearchFriendFragment() {
        if (searchFriendFragment == null) {
            searchFriendFragment = new SearchFriendFragment();
            searchFriendFragment.setListeners(this);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, searchFriendFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showResultSearchFriend(String username) {
        setUsername(username);
        showResultSearchFriendFragment();
    }

    private void showResultSearchFriendFragment() {
        if (resultSearchFriendFragment == null) {
            resultSearchFriendFragment = new ResultSearchFriendFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, resultSearchFriendFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_messages:
                showMessagesFragment();
                break;
            case R.id.btn_show_contacts:
                showContactsFragment();
                break;
            case R.id.btn_show_room:
                showRoomChat();
                break;
            default:
                break;
        }
    }

    private void showRoomChat() {
        if (roomChatFragment == null) {
            roomChatFragment = new RoomChatFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, roomChatFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showMessagesFragment() {
        if (messagesFragment == null) {
            messagesFragment = new MessagesFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, messagesFragment)
                .addToBackStack(null)
                .commit();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

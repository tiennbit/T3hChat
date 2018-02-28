package tuannm.com.chatdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.base.BaseFragment;
import tuannm.com.chatdemo.dto.MessageDto;
import tuannm.com.chatdemo.model.User;
import tuannm.com.chatdemo.presenter.ChatRoomPresenterImpl;
import tuannm.com.chatdemo.view.adapter.MessageAdapter;

/**
 * Created by TNM on 2/17/2018.
 */

public class RoomChatFragment extends BaseFragment implements RoomChatView, View.OnClickListener {
    private RecyclerView rcvChatContents;
    private List<MessageDto> messages;
    private MessageAdapter contentAdapter;

    private ImageView btnSend;
    private EditText edtChatContent;
    private ChatRoomPresenterImpl chatRoomPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatRoomPresenter = new ChatRoomPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_chat_room;
    }

    @Override
    public void initializeComponents() {
        btnSend = rootView.findViewById(R.id.btn_send);
        edtChatContent = rootView.findViewById(R.id.edt_chat);
        btnSend.setOnClickListener(this);

        rcvChatContents = rootView.findViewById(R.id.rcv_chat_contents);
        rcvChatContents.setLayoutManager(new LinearLayoutManager(getContext()));
        messages = new ArrayList<>();
        contentAdapter = new MessageAdapter(getContext(), messages);
        rcvChatContents.setAdapter(contentAdapter);

        chatRoomPresenter.receiveMessage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                chatRoomPresenter.sendMessage(edtChatContent.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void receiveMessa(MessageDto messageDto) {
        messages.add(messageDto);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                contentAdapter.notifyDataSetChanged();
                edtChatContent.setText("");
            }
        });
    }
}

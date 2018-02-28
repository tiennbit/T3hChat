package tuannm.com.chatdemo.view.fragment;

import java.util.List;

import tuannm.com.chatdemo.dto.UserDto;

/**
 * Created by TNM on 2/23/2018.
 */

public interface ContactsView {
    void getUser(List<UserDto> users);
}

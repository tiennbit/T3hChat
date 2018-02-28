package tuannm.com.chatdemo.dto;

/**
 * Created by TNM on 2/23/2018.
 */

public class UserDto {
    private String username;
    private byte[] bytes;

    public UserDto(String username, byte[] bytes) {
        this.username = username;
        this.bytes = bytes;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getBytes() {
        return bytes;
    }
}

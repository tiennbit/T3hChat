package tuannm.com.chatdemo.dto;

/**
 * Created by TNM on 2/19/2018.
 */

public class MessageDto {
    private String sender;
    private String message;

    public MessageDto(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}

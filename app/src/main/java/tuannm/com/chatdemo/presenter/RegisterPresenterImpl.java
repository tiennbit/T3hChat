package tuannm.com.chatdemo.presenter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import io.socket.emitter.Emitter;
import tuannm.com.chatdemo.App;
import tuannm.com.chatdemo.view.fragment.RegisterView;

/**
 * Created by TNM on 2/12/2018.
 */

public class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView registerView;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Override
    public void register(String username, String password, String rePassword, ImageView img) {
        if (username.trim().isEmpty() || password.trim().isEmpty() || rePassword.isEmpty()) {
            registerView.registerFailure("Phải điền đầy đủ thông tin!");
            return;
        }
        if (username.trim().length() < 4) {
            registerView.registerFailure("Tên sử dụng phải 4 ký tự trở lên!");
            return;
        }
        if (password.trim().length() < 6) {
            registerView.registerFailure("Mật khẩu phải 6 ký tự trở lên!");
            return;
        }

        if (!password.equals(rePassword)) {
            registerView.registerFailure("Mật khẩu không khớp!");
            return;
        }

        JSONObject user = new JSONObject();
        try {
            user.put("username", username);
            user.put("password", password);
            user.put("image", getByteArrayFromImg(img));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //gửi thông tin người đăng kí lên server
        App.mSocket.emit("client-send-register", user);
        //nhận kết quả tài khoản này đã tồn tại
        App.mSocket.on("server-send-register-fail", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                registerView.registerFailure("Tài khoản đã tồn tại!");
            }
        });
        //Nhận kết quả đăng kí thành công:
        App.mSocket.on("server-send-register-success", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                registerView.registerSuccess();
            }
        });
    }

    private byte[] getByteArrayFromImg(ImageView img) {
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }
}

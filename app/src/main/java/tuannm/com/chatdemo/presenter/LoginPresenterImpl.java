package tuannm.com.chatdemo.presenter;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.emitter.Emitter;
import tuannm.com.chatdemo.App;
import tuannm.com.chatdemo.base.BasePresenter;
import tuannm.com.chatdemo.view.fragment.LoginView;

/**
 * Created by TNM on 2/12/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void checkLogin(String username, String password) {
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            loginView.loginFailure("Không được để trống");
            return;
        }
        if (username.length() < 4 || password.length() < 6) {
            loginView.loginFailure("Tên đăng nhập hoặc mật khẩu không đúng");
            return;
        }
        //gửi data lên server
        JSONObject user = new JSONObject();
        try {
            user.put("username", username);
            user.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        App.mSocket.emit("client-send-login", user);

        App.mSocket.on("server-send-not-user", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                loginView.loginFailure("Tài khoản không tồn tại!");
            }
        });
        App.mSocket.on("server-send-wrong-password", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                loginView.loginFailure("Mật khẩu không đúng!");
            }
        });
        App.mSocket.on("server-send-login-success", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                loginView.loginSuccess();
            }
        });
    }
}

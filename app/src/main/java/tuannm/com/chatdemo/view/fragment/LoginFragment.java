package tuannm.com.chatdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.base.BaseFragment;
import tuannm.com.chatdemo.presenter.LoginPresenterImpl;

/**
 * Created by TNM on 2/12/2018.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, LoginView {
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView btnGoRegister;

    private LoginPresenterImpl loginPresenter;
    private OnLoginFragmentListeners listeners;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initializeComponents() {
        edtUsername = rootView.findViewById(R.id.edt_username);
        edtPassword = rootView.findViewById(R.id.edt_password);
        btnLogin = rootView.findViewById(R.id.btn_login);
        btnGoRegister = rootView.findViewById(R.id.btn_go_register);

        btnLogin.setOnClickListener(this);
        btnGoRegister.setOnClickListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginPresenter.checkLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.btn_go_register:
                listeners.goToRegister();
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Login Success!", Toast.LENGTH_SHORT).show();
            }
        });
        listeners.goToMainActivity();
    }

    @Override
    public void loginFailure(final String error) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnLoginFragmentListeners{
        void goToRegister();
        void goToMainActivity();
    }

    public void setListeners(OnLoginFragmentListeners listeners) {
        this.listeners = listeners;
    }
}

package tuannm.com.chatdemo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.base.BaseActivity;
import tuannm.com.chatdemo.view.fragment.LoginFragment;
import tuannm.com.chatdemo.view.fragment.RegisterFragment;

/**
 * Created by TNM on 2/12/2018.
 */

public class LoginAndRegisterActivity extends BaseActivity implements LoginFragment.OnLoginFragmentListeners, RegisterFragment.OnRegisterFragmentListeners {
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login_register;
    }

    @Override
    public void initializeComponents() {
        showLoginFragment();
    }

    private void showLoginFragment() {
        if(loginFragment == null){
            loginFragment = new LoginFragment();
            loginFragment.setListeners(this);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, loginFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showRegisterFragment() {
        if(registerFragment == null){
            registerFragment = new RegisterFragment();
            registerFragment.setListeners(this);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, registerFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToRegister() {
        showRegisterFragment();
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToLogin() {
        showLoginFragment();
    }
}

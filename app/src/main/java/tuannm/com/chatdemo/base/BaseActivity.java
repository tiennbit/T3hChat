package tuannm.com.chatdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by TNM on 2/12/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements ViewInitialize {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int contentViewId = getContentViewId();
        setContentView(contentViewId);
        initializeComponents();
    }

    protected void showNotification(String notification){
        Toast.makeText(this, notification, Toast.LENGTH_SHORT).show();
    }
}

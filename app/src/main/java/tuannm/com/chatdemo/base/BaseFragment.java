package tuannm.com.chatdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TNM on 2/12/2018.
 */

public abstract class BaseFragment extends Fragment implements ViewInitialize {
    protected View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int contentViewId = getContentViewId();
        rootView = inflater.inflate(contentViewId, container, false);
        initializeComponents();
        return rootView;
    }
}

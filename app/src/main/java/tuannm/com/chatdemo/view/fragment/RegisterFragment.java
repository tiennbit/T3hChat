package tuannm.com.chatdemo.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.base.BaseFragment;
import tuannm.com.chatdemo.constant.RequestCode;
import tuannm.com.chatdemo.presenter.RegisterPresenterImpl;

/**
 * Created by TNM on 2/12/2018.
 */

public class RegisterFragment extends BaseFragment implements View.OnClickListener, RegisterView {
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtConfirmPassword;

    private Button btnRegister;

    private ImageView btnTakePicture;
    private ImageView btnChoosePicture;

    private CircleImageView imgAvatar;

    private RegisterPresenterImpl registerPresenter;

    private OnRegisterFragmentListeners listeners;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_register;
    }

    @Override
    public void initializeComponents() {
        edtUsername = rootView.findViewById(R.id.edt_username);
        edtPassword = rootView.findViewById(R.id.edt_password);
        edtConfirmPassword = rootView.findViewById(R.id.edt_confirm_password);
        btnTakePicture = rootView.findViewById(R.id.btn_take_picture);
        btnChoosePicture = rootView.findViewById(R.id.btn_choose_picture);
        imgAvatar = rootView.findViewById(R.id.img_avatar_register);
        btnRegister = rootView.findViewById(R.id.btn_register);

        btnTakePicture.setOnClickListener(this);
        btnChoosePicture.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPresenter = new RegisterPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                registerPresenter.register(edtUsername.getText().toString(),
                        edtPassword.getText().toString(),
                        edtConfirmPassword.getText().toString(),
                        imgAvatar);
                break;
            case R.id.btn_take_picture:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RequestCode.REQUEST_TAKE_PICTURE);
                break;
            case R.id.btn_choose_picture:
                Intent intent2 = new Intent(Intent.ACTION_PICK);
                intent2.setType("image/*");
                startActivityForResult(intent2, RequestCode.REQUEST_CHOOSE_PICTURE);
                break;
            default:
                break;
        }
    }

    @Override
    public void registerSuccess() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Register Success!", Toast.LENGTH_SHORT).show();
            }
        });
        listeners.goToLogin();
    }

    @Override
    public void registerFailure(final String error) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnRegisterFragmentListeners {
        void goToLogin();
    }

    public void setListeners(OnRegisterFragmentListeners listeners) {
        this.listeners = listeners;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.REQUEST_TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgAvatar.setImageBitmap(bitmap);
        } else if (requestCode == RequestCode.REQUEST_CHOOSE_PICTURE && resultCode == Activity.RESULT_OK) {
            try {
                Uri uri = data.getData();
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAvatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

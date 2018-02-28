package tuannm.com.chatdemo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.constant.RequestCode;

/**
 * Created by TNM on 2/22/2018.
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtConfirmPassword;

    private Button btnRegister;

    private ImageView btnTakePicture;
    private ImageView btnChoosePicture;

    private CircleImageView imgAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();
    }

    private void initView() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        btnTakePicture = findViewById(R.id.btn_take_picture);
        btnChoosePicture = findViewById(R.id.btn_choose_picture);
        imgAvatar = findViewById(R.id.img_avatar_register);
        btnRegister = findViewById(R.id.btn_register);

        btnTakePicture.setOnClickListener(this);
        btnChoosePicture.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:

                break;
            case R.id.btn_take_picture:
                takePicture();
                break;
            case R.id.btn_choose_picture:
                choosePicture();
                break;
            default:
                break;
        }
    }

    private void choosePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, RequestCode.REQUEST_CHOOSE_PICTURE);
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RequestCode.REQUEST_TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.REQUEST_TAKE_PICTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] bytes =  stream.toByteArray();
//            Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            imgAvatar.setImageBitmap(bitmap);
        } else if (requestCode == RequestCode.REQUEST_CHOOSE_PICTURE && resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAvatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

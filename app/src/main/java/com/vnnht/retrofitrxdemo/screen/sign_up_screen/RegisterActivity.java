package com.vnnht.retrofitrxdemo.screen.sign_up_screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.vnnht.retrofitrxdemo.R;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener, RegisterContract.View {

    private static final int REQUEST_CODE = 69;

    private EditText mEditUsername, mEditPassword;
    private ImageView mImageAvatar;
    private String mRealPath = "";
    RegisterContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setView();
    }

    private void setView() {
        Button buttonSignUp = findViewById(R.id.button_signup);
        mEditPassword = findViewById(R.id.edit_password);
        mEditUsername = findViewById(R.id.edit_username);
        mImageAvatar = findViewById(R.id.image_avatar);
        mPresenter = new RegisterPresenter(this);
        buttonSignUp.setOnClickListener(this);
        mImageAvatar.setOnClickListener(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_avatar:
                requestStoragePermission();
                break;
            case R.id.button_signup:
                setActionSignUp();
                break;
        }
    }

    private void setActionSignUp() {
        final String username = mEditUsername.getText().toString();
        final String password = mEditPassword.getText().toString();
        mPresenter.insertStudent(username,password,mRealPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            mRealPath = getRealPathFromURI(uri);
            try {
                InputStream inputStream =
                        getContentResolver().openInputStream(uri); //get image from uri
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream); // convert ve bitmap
                mImageAvatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
        }
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    @Override
    public void onInsertSuccess(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}

package com.vnnht.retrofitrxdemo.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.vnnht.retrofitrxdemo.R;
import com.vnnht.retrofitrxdemo.screen.list_student_screen.ListStudentActivity;
import com.vnnht.retrofitrxdemo.screen.sign_up_screen.RegisterActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, LoginContract.View {

    private Button mButtonSignUp, mButtonLogin;
    private EditText mEditUsername, mEditPassword;
    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
    }

    private void setView() {
        mButtonSignUp = findViewById(R.id.button_signup);
        mEditUsername = findViewById(R.id.edit_username);
        mEditPassword = findViewById(R.id.edit_password);
        mButtonLogin = findViewById(R.id.button_login);
        mPresenter = new LoginPresenter(this);
        mButtonSignUp.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_signup:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.button_login:
                setLoginButtonHandle();
        }
    }

    private void setLoginButtonHandle() {
        String username = mEditUsername.getText().toString();
        String password = mEditPassword.getText().toString();
        mPresenter.checkLogin(username, password);
    }

    @Override
    public void loginSuccess(String mess) {
        Toast.makeText(this, "Login success !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ListStudentActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFail(String fail) {
        Toast.makeText(this, "ERROR: " + fail, Toast.LENGTH_SHORT).show();
    }
}

package com.vnnht.retrofitrxdemo.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.vnnht.retrofitrxdemo.R;
import com.example.vnnht.retrofitrxdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainModelView mMainModelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mMainModelView = new MainModelView(this.getApplicationContext());
        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(mMainModelView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainModelView.onStart();
    }

    @Override
    protected void onStop() {
        mMainModelView.onStop();
        super.onStop();
    }
}

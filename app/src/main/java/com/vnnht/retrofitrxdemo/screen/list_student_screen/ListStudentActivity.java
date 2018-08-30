package com.vnnht.retrofitrxdemo.screen.list_student_screen;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.vnnht.retrofitrxdemo.R;
import com.example.vnnht.retrofitrxdemo.databinding.ActivityListStudentBinding;

public class ListStudentActivity extends AppCompatActivity {

    private ListStudentViewModel mListStudentViewModel;

    public static Intent getInstance(Context context) {
        Intent intent = new Intent(context, ListStudentActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ListStudentAdapter listStudentAdapter = new ListStudentAdapter();
        mListStudentViewModel = new ListStudentViewModel(this, listStudentAdapter);
        ActivityListStudentBinding activityListStudentBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_list_student);
        activityListStudentBinding.setViewModel(mListStudentViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mListStudentViewModel.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mListStudentViewModel.onStop();
    }
}

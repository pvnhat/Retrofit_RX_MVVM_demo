package com.vnnht.retrofitrxdemo.screen.list_student_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.vnnht.retrofitrxdemo.R;
import com.vnnht.retrofitrxdemo.data.model.Student;
import java.util.ArrayList;
import java.util.List;

public class ListStudentActivity extends AppCompatActivity
        implements ListStudentContract.View, OnItemClickListener {

    private ListStudentContract.Presenter mPresenter;
    private ListStudentAdapter mListStudentAdapter;
    private List<Student> mStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        initView();
    }

    private void initView() {
        RecyclerView mRecyclerStudent = findViewById(R.id.recycler_list_student);
        mPresenter = new ListStudentPresenter(this);
        mPresenter.onRequestData();
        mListStudentAdapter = new ListStudentAdapter(this, this);
        mRecyclerStudent.setHasFixedSize(true);
        mRecyclerStudent.setAdapter(mListStudentAdapter);
    }

    @Override
    public void onGetDataSuccess(List<Student> studentList) {
        mStudentList = new ArrayList<>();
        if (studentList != null) {
            mStudentList = studentList;
            mListStudentAdapter.updateStudentList(mStudentList);
        }
    }

    @Override
    public void onFail(String ex) {
        Log.d("ERROR ", ex);
    }

    @Override
    public void onItemClick() {

    }
}

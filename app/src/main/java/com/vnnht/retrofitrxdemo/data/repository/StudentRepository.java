package com.vnnht.retrofitrxdemo.data.repository;

import com.vnnht.retrofitrxdemo.data.StudentDataSource;
import com.vnnht.retrofitrxdemo.data.model.Student;
import com.vnnht.retrofitrxdemo.data.source.remote.StudentRemoteDataSource;
import io.reactivex.Observable;
import java.util.List;

public class StudentRepository {
    private StudentDataSource.RemoteDataSource mStudentDataSource;

    public StudentRepository() {
        mStudentDataSource = new StudentRemoteDataSource();
    }

    public Observable<List<Student>> getRemoteDataSource(String username, String password) {
        return mStudentDataSource.getLoginData(username, password);
    }

    public Observable<List<Student>> getRemoteDataSource(int limit) {
        return mStudentDataSource.getStudentData(limit);
    }
}

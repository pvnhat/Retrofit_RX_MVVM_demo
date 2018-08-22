package com.vnnht.retrofitrxdemo.data.repository;

import com.vnnht.retrofitrxdemo.data.StudentDataSource;
import com.vnnht.retrofitrxdemo.data.model.Student;
import com.vnnht.retrofitrxdemo.data.source.remote.DataClientAct;
import com.vnnht.retrofitrxdemo.data.source.remote.StudentRemoteDataSoure;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.MultipartBody;

public class StudentRepository {
    private StudentDataSource mStudentDataSource;

    public StudentRepository() {
        mStudentDataSource = new StudentRemoteDataSoure();
    }

    public Observable<String> getRemoteDataSource(MultipartBody.Part photo) {
        return mStudentDataSource.getStudentData().upLoadPhoto(photo);
    }

    public Observable<String> getRemoteDataSource(String username, String password,
            String avatarLink) {
        return mStudentDataSource.getStudentData().insertStudent(username, password, avatarLink);
    }

    public Observable<List<Student>> getRemoteDataSource(String username, String password) {
        return mStudentDataSource.getStudentData().getLoginData(username, password);
    }

    public Observable<List<Student>> getRemoteDataSource(int limit) {
        return mStudentDataSource.getStudentData().getAllStudent(limit);
    }
}

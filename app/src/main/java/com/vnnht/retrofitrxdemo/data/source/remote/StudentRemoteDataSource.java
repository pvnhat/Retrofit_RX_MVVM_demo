package com.vnnht.retrofitrxdemo.data.source.remote;

import com.vnnht.retrofitrxdemo.data.StudentDataSource;
import com.vnnht.retrofitrxdemo.data.model.Student;
import io.reactivex.Observable;
import java.util.List;

public class StudentRemoteDataSource implements StudentDataSource.RemoteDataSource {

    //public static final String LINK_URL = "http://172.16.36.126/RetrofitRX/"; // my home
    private static final String LINK_URL = "http://192.168.56.1/RetrofitRX/"; // framgia

    private static DataClientAct getDataRX() {
        return RetrofitData.getStudentDataRX(LINK_URL).create(DataClientAct.class);
    }

    @Override
    public Observable<List<Student>> getStudentData(int limit) {
        return getDataRX().getAllStudent(limit);
    }

    @Override
    public Observable<List<Student>> getLoginData(String userName, String password) {
        return getDataRX().getLoginData(userName, password);
    }
}

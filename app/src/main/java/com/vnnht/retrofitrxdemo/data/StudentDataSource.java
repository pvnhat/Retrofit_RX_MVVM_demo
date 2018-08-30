package com.vnnht.retrofitrxdemo.data;

import com.vnnht.retrofitrxdemo.data.model.Student;
import io.reactivex.Observable;
import java.util.List;

public interface StudentDataSource {

    interface RemoteDataSource { // De cho sutdent implêmnen các phương thức của nó
        Observable<List<Student>> getStudentData(int limit);

        Observable<List<Student>> getLoginData(String userName, String password);
    }

    interface LocalDataSource {

    }
}

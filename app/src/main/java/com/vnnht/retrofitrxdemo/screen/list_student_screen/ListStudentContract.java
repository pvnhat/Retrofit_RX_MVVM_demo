package com.vnnht.retrofitrxdemo.screen.list_student_screen;

import com.vnnht.retrofitrxdemo.data.model.Student;
import java.util.List;

public interface ListStudentContract {
    interface View {
        void onGetDataSuccess(List<Student> studentList);

        void onFail(String ex);
    }

    interface Presenter {
        void onRequestData();
    }
}

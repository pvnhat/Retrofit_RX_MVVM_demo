package com.vnnht.retrofitrxdemo.screen.list_student_screen;

import android.databinding.ObservableField;
import android.view.View;
import com.vnnht.retrofitrxdemo.data.model.Student;
import java.util.Objects;

public class ItemStudentViewModel {
    public ObservableField<Student> mStudentObservableField = new ObservableField<>();
    private OnItemClickListener mOnItemClickListener;

    public ItemStudentViewModel(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public String getImageUrl() {
        return Objects.requireNonNull(mStudentObservableField.get()).getAvatar();
    }

    public void setStudent(Student student) {
        mStudentObservableField.set(student);
    }

    public void onItemClicked(View view) {
        if (mOnItemClickListener == null || mStudentObservableField.get() == null) {
            return;
        }
        mOnItemClickListener.onItemClick(mStudentObservableField.get().getUsername());
    }
}

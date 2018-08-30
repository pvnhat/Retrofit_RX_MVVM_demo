package com.vnnht.retrofitrxdemo.screen.list_student_screen;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.vnnht.retrofitrxdemo.R;
import com.example.vnnht.retrofitrxdemo.databinding.StudentRowBinding;
import com.vnnht.retrofitrxdemo.data.model.Student;
import java.util.ArrayList;
import java.util.List;

public class ListStudentAdapter extends RecyclerView.Adapter<ListStudentAdapter.ViewHolder> {

    private List<Student> mStudentList;
    private OnItemClickListener mOnItemClickListener;

    ListStudentAdapter() {
        mStudentList = new ArrayList<>();
    }

    public void updateStudentList(List<Student> studentList) {
        if (mStudentList != null) {
            mStudentList.clear();
        }
        mStudentList = studentList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StudentRowBinding studentRowBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.student_row, parent, false);
        return new ViewHolder(studentRowBinding, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getViewHolder(mStudentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStudentList != null ? mStudentList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private StudentRowBinding mStudentRowBinding;
        private ItemStudentViewModel mItemStudentViewModel;

        ViewHolder(StudentRowBinding studentRowBinding, OnItemClickListener itemClickListener) {
            super(studentRowBinding.getRoot());
            mStudentRowBinding = studentRowBinding;
            mItemStudentViewModel = new ItemStudentViewModel(itemClickListener);
            mStudentRowBinding.setViewModel(mItemStudentViewModel);
        }

        void getViewHolder(Student student) {
            mItemStudentViewModel.setStudent(student);
            mStudentRowBinding.executePendingBindings(); // thuc thi binding
        }
    }
}

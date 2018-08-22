package com.vnnht.retrofitrxdemo.screen.list_student_screen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.vnnht.retrofitrxdemo.R;
import com.vnnht.retrofitrxdemo.data.model.Student;
import java.util.ArrayList;
import java.util.List;

public class ListStudentAdapter extends RecyclerView.Adapter<ListStudentAdapter.ViewHolder> {

    private Context mContext;
    private List<Student> mStudentList;
    private OnItemClickListener mOnItemClickListener;

    public ListStudentAdapter(Context context, OnItemClickListener onItemClickListener) {
        mContext = context;
        mStudentList = new ArrayList<>();
        mOnItemClickListener = onItemClickListener;
    }

    public void updateStudentList(List<Student> studentList) {
        if (mStudentList != null) {
            mStudentList.clear();
        }
        mStudentList = studentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mStudentList != null ? mStudentList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener {

        private TextView mTextId, mTextUsername;
        private ImageView mImageAvatar;

        ViewHolder(View itemView) {
            super(itemView);
            mTextId = itemView.findViewById(R.id.text_id);
            mTextUsername = itemView.findViewById(R.id.text_username);
            mImageAvatar = itemView.findViewById(R.id.image_avatar);
        }

        void getViewHolder(ViewHolder holder, int position) {
            Glide.with(mContext).load(mStudentList.get(position).getAvatar()).into(holder.mImageAvatar);
            holder.mTextId.setText(mStudentList.get(position).getStudentId());
            holder.mTextUsername.setText(mStudentList.get(position).getUsername());
        }

        @Override
        public void onItemClick() {

        }
    }
}

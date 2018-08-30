package com.vnnht.retrofitrxdemo.screen.list_student_screen;

import android.content.Context;
import com.example.vnnht.retrofitrxdemo.R;
import com.vnnht.retrofitrxdemo.data.model.Student;
import com.vnnht.retrofitrxdemo.data.repository.StudentRepository;
import com.vnnht.retrofitrxdemo.screen.BaseViewModel;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ListStudentViewModel extends BaseViewModel
        implements OnItemClickListener, Observer<List<Student>> {

    private ListStudentAdapter mAdapter;
    private Context mContext;
    private StudentRepository mStudentRepository;

    public ListStudentViewModel(Context context, ListStudentAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
        mStudentRepository = new StudentRepository();
        mAdapter.setOnClickListener(this);

        onRequestData();
    }

    public void onRequestData() {
        Observable<List<Student>> observable = mStudentRepository.getRemoteDataSource(15)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(this);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    public void onItemClick(String username) {
        if (username == null) {
            Toasty.error(mContext, mContext.getString(R.string.text_null)).show();
        } else {
            Toasty.info(mContext, username).show();
        }
    }

    public ListStudentAdapter getAdapterABCD() {
        return mAdapter;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<Student> studentList) {
        if (studentList != null) {
            mAdapter.updateStudentList(studentList);
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}

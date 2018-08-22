package com.vnnht.retrofitrxdemo.screen.list_student_screen;

import com.vnnht.retrofitrxdemo.data.model.Student;
import com.vnnht.retrofitrxdemo.data.repository.StudentRepository;
import com.vnnht.retrofitrxdemo.data.source.remote.DataClientAct;
import com.vnnht.retrofitrxdemo.data.source.remote.StudentRemoteDataSoure;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ListStudentPresenter
        implements ListStudentContract.Presenter, Observer<List<Student>> {

    ListStudentContract.View mView;
    StudentRepository mStudentRepository;

    public ListStudentPresenter(ListStudentContract.View view) {
        mView = view;
        mStudentRepository = new StudentRepository();
    }

    @Override
    public void onRequestData() {
        Observable<List<Student>> observable = mStudentRepository.getRemoteDataSource(15)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<Student> studentList) {
        if (studentList.size() > 0) {
            mView.onGetDataSuccess(studentList);
        }
    }

    @Override
    public void onError(Throwable e) {
        mView.onFail(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}

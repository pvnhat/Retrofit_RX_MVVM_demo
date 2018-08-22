package com.vnnht.retrofitrxdemo.screen.main;

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

public class LoginPresenter implements LoginContract.Presenter, Observer<List<Student>> {

    LoginContract.View mView;
    String mUsername;
    StudentRepository mStudentRepository;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mStudentRepository = new StudentRepository();
    }

    @Override
    public void checkLogin(String username, String password) {
        mUsername = username;
        Observable<List<Student>> observable = mStudentRepository.getRemoteDataSource(username, password)
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
            mView.loginSuccess(" with : " + mUsername);
        }
    }

    @Override
    public void onError(Throwable e) {
        mView.loginFail(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}

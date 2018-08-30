package com.vnnht.retrofitrxdemo.screen.main;

import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.view.View;
import com.example.vnnht.retrofitrxdemo.R;
import com.vnnht.retrofitrxdemo.data.model.Student;
import com.vnnht.retrofitrxdemo.data.repository.StudentRepository;
import com.vnnht.retrofitrxdemo.screen.BaseViewModel;
import com.vnnht.retrofitrxdemo.screen.list_student_screen.ListStudentActivity;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MainModelView extends BaseViewModel {

    private Context mContext;
    private StudentRepository mStudentRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Observable.OnPropertyChangedCallback mOnPropertyChangedCallback = getChangedCallBack();

    public ObservableField<String> mTextUsername = new ObservableField<>();
    public ObservableField<String> mTextPassword = new ObservableField<>();

    public MainModelView(Context context) {
        mContext = context;
        mStudentRepository = new StudentRepository();
    }

    private Observable.OnPropertyChangedCallback getChangedCallBack() {
        return new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                validateTextInput();
            }
        };
    }

    private boolean validateTextInput() {
        String usernameInput = this.mTextUsername.get();
        String passwordInput = this.mTextPassword.get();
        if (usernameInput == null || passwordInput == null) {
            return false;
        }
        return true;
    }

    public void onLoginButtonClick(View view) {
        boolean isInputEmpty = validateTextInput();
        if (isInputEmpty) {
            checkLogin(mTextUsername.get(), mTextPassword.get());
        }
    }

    public void onSignupButtonClick(View view) {
        Toasty.info(mContext, mContext.getString(R.string.text_comming)).show();
    }

    private void checkLogin(String username, String password) {
        Disposable disposable = mStudentRepository.getRemoteDataSource(username, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(List<Student> studentList) throws Exception {
                        if (studentList.size() > 0) {
                            Toasty.success(mContext, mContext.getString(R.string.text_login_success)
                                    + mTextUsername.get()).show();
                            mContext.startActivity(ListStudentActivity.getInstance(mContext));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toasty.error(mContext, mContext.getString(R.string.text_failed_login)
                                + throwable.getMessage()).show();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onStart() {
        mTextUsername.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mTextPassword.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
    }

    @Override
    protected void onStop() {
        mTextUsername.removeOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mTextPassword.removeOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mCompositeDisposable.clear();
    }
}

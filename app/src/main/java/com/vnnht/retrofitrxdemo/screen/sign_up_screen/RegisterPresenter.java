package com.vnnht.retrofitrxdemo.screen.sign_up_screen;

import android.util.Log;
import android.widget.Toast;
import com.vnnht.retrofitrxdemo.data.repository.StudentRepository;
import com.vnnht.retrofitrxdemo.data.source.remote.DataClientAct;
import com.vnnht.retrofitrxdemo.data.source.remote.StudentRemoteDataSoure;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegisterPresenter implements RegisterContract.Presenter, Observer<String> {

    RegisterContract.View mView;
    String mUsername, mPassword;
    StudentRepository mStudentRepository;

    public RegisterPresenter(RegisterContract.View view) {
        mView = view;
        mStudentRepository = new StudentRepository();
    }

    @Override
    public void insertStudent(String username, String password, String realPath) {
        mUsername = username;
        mPassword = password;
        if (realPath.length() > 0 && username.length() > 0 && password.length() > 0) {
            File file = new File(realPath);
            String filePath = file.getAbsolutePath();
            String[] fileArr = filePath.split("\\.");
            filePath = fileArr[0] + System.currentTimeMillis();
            filePath = filePath + "." + fileArr[1];

            // xac nhan lai kieu file
            RequestBody requestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // cung key voi server
            MultipartBody.Part bodyPart =
                    MultipartBody.Part.createFormData("upload_image", filePath, requestBody);

            Observable<String> observable = mStudentRepository.getRemoteDataSource(bodyPart)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
            observable.subscribe(this);
        } else {
            mView.onFail("Data isn't enough !");
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String s) {
        if (s.length() > 0) {
            Observable<String> observerInsert =
                    mStudentRepository.getRemoteDataSource(mUsername, mPassword,
                            StudentRemoteDataSoure.LINK_URL + "AvatarImage/" + s)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread());
            observerInsert.subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(String s) {
                    if (s.equals("insert_success")) {
                        mView.onInsertSuccess("Inserted !");
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("LOG ERROR INSERT: ", e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}

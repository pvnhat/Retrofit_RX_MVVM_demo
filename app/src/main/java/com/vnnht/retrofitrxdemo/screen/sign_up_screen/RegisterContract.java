package com.vnnht.retrofitrxdemo.screen.sign_up_screen;

public interface RegisterContract {
    interface View {
        void onInsertSuccess(String mess);

        void onFail(String error);
    }

    interface Presenter {
        void insertStudent(String username, String password, String realPath);
    }
}

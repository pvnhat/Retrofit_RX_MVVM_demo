package com.vnnht.retrofitrxdemo.screen.main;

public interface LoginContract {
    interface View {
        void loginSuccess(String username);
        void loginFail(String fail);
    }

    interface Presenter {
        void checkLogin(String username, String password);
    }
}

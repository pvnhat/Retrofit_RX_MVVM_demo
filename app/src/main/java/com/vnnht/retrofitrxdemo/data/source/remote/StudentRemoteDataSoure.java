package com.vnnht.retrofitrxdemo.data.source.remote;

import com.vnnht.retrofitrxdemo.data.StudentDataSource;

public class StudentRemoteDataSoure implements StudentDataSource{

    //public static final String LINK_URL = "http://172.16.36.126/RetrofitRX/";
    public static final String LINK_URL = "http://192.168.56.1/RetrofitRX/";

    public static DataClientAct getDataRX() {
        return RetrofitData.getStudentDataRX(LINK_URL).create(DataClientAct.class);
    }

    @Override
    public DataClientAct getStudentData() {
        return getDataRX();
    }
}

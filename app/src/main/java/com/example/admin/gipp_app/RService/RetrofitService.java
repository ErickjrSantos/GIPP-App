package com.example.admin.gipp_app.RService;

import com.example.admin.gipp_app.Modelo.LoginDAO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by admin on 26/07/2017.
 */

public interface RetrofitService {
    @POST("")
    Call<LoginDAO> convertelogin(@Field("from-type")String from_type,
                                @Field("from-value")String from_value);
}

package com.son.getlistapi.Model;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
//    String BASE_URL = "https://api.github.com/";
    @GET("users/blackmiaool/repos")
    Call<List<Detail>> getList();
}

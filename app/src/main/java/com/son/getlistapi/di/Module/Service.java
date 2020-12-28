package com.son.getlistapi.di.Module;

import com.son.getlistapi.Model.Detail;
import com.son.getlistapi.Model.RetrofitService;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import retrofit2.Call;

public class Service {
    private final RetrofitService retrofitService;

    @Inject
    public Service(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    public Call<List<Detail>> getList() {
        return retrofitService.getList();
    }
}

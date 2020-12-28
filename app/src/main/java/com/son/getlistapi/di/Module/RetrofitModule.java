package com.son.getlistapi.di.Module;

import com.son.getlistapi.Model.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private static final String BASE_URL = "https://api.github.com/";

    @Singleton
    @Provides
    static Retrofit retrofitProvide(){
       return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Singleton
    @Provides
    static RetrofitService retrofitServiceProvide(Retrofit retrofit){
        return retrofit.create(RetrofitService.class);
    }

}

package com.son.getlistapi.View;

import android.app.Application;

import com.son.getlistapi.di.Component.DaggerDetailComponent;
import com.son.getlistapi.di.Component.DetailComponent;
import com.son.getlistapi.di.Module.RetrofitModule;


public class MyApplication extends Application {
    DetailComponent detailComponent;

    @Override
    public void onCreate() {
        super.onCreate();
            detailComponent = DaggerDetailComponent.builder()
                    .retrofitModule(new RetrofitModule()).build();
    }
    public DetailComponent getApplicationComponent(){
        return detailComponent;
    }
}

package com.son.getlistapi.di.Component;

import com.son.getlistapi.View.MainActivity;
import com.son.getlistapi.di.Module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface DetailComponent  {
   void inject(MainActivity abc);
}

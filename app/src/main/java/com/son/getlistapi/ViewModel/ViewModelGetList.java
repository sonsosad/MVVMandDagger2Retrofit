package com.son.getlistapi.ViewModel;
import android.text.Editable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.son.getlistapi.Model.Detail;
import com.son.getlistapi.di.Module.Service;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewModelGetList extends ViewModel {

    public MutableLiveData<List<Detail>> detailList;
    public List<Detail> listSearch;
    public List<Detail> listFor = new ArrayList<>();

    @Inject
    Service service;
    @Inject
    public ViewModelGetList(){
        detailList = new MutableLiveData<List<Detail>>();
    }

    public LiveData<List<Detail>> getDetailList() {
        if (detailList != null) {
            loadData(service);
        }
        return detailList;
    }

    public void loadData(Service service) {
//        myApplication.getApplicationComponent().inject(this);
        service.getList().enqueue(new Callback<List<Detail>>() {
            @Override
            public void onResponse(Call<List<Detail>> call, Response<List<Detail>> response) {
                detailList.setValue(response.body());
                getData(response.body());
            }

            @Override
            public void onFailure(Call<List<Detail>> call, Throwable t) {

            }
        });
    }

    public void getData(List<Detail> list) {
        listFor = list;
    }

    public void searchByName(Editable key) {
        listSearch = new ArrayList<>();

        for (Detail item : listFor) {

            if (item.getName().toLowerCase().contains(key.toString()) || item.getFullName().toLowerCase().contains(key.toString())) {
                listSearch.add(item);

            }
        }

    }

    public List<Detail> getListSearch() {
        return listSearch;
    }

    //    private void loadData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RetrofitService.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//        Call<List<Detail>> call = retrofitService.getList();
//        call.enqueue(new Callback<List<Detail>>() {
//            @Override
//            public void onResponse(Call<List<Detail>> call, Response<List<Detail>> response) {
//                detailList.setValue(response.body());
//                getData(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Detail>> call, Throwable t) {
//
//            }
//        });
//    }
}

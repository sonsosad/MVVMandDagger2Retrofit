package com.son.getlistapi.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import com.son.getlistapi.Adapter.GetListAdapter;
import com.son.getlistapi.Model.Detail;
import com.son.getlistapi.R;
import com.son.getlistapi.ViewModel.ViewModelGetList;
import com.son.getlistapi.di.Module.Service;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvDetail;
    GetListAdapter getListAdapter;
    EditText edtSearch;
    ImageView imgSearch;
    Context context;
    List<Detail> list = new ArrayList();



    @Inject
    ViewModelGetList viewModelGetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        ((MyApplication) getApplication()).getApplicationComponent().inject(this);
        context = getApplicationContext();
        rvDetail.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rvDetail.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        getListAdapter = new GetListAdapter(list, context);
        rvDetail.setAdapter(getListAdapter);
//        ViewModelGetList viewModelGetList = ViewModelProviders.of(this).get(ViewModelGetList.class);

        viewModelGetList.getDetailList().observe(this, new Observer<List<Detail>>() {
            @Override
            public void onChanged(List<Detail> list) {
                MainActivity.this.list.clear();
                MainActivity.this.list.addAll(list);
                getListAdapter.notifyDataSetChanged();
                Log.e("Tag","listLiveData"+list.size());
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewModelGetList.searchByName(editable);
                getListAdapter.updateData(viewModelGetList.getListSearch());
            }
        });

    }



    private void setUpView() {
        rvDetail = findViewById(R.id.rvDetail);
        edtSearch = findViewById(R.id.edtSearch);
        imgSearch = findViewById(R.id.imgSearch);
    }

}
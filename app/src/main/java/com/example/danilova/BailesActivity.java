package com.example.danilova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BailesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bailes);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(BailesActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        androidData = new DataClass("Ballet", R.string.ballet, R.drawable.img_ballet);
        dataList.add(androidData);

        androidData = new DataClass("Gimnasia", R.string.gimnasia, R.drawable.img_gimnasia);
        dataList.add(androidData);

        androidData = new DataClass("Folklore", R.string.folklore, R.drawable.img_folklore);
        dataList.add(androidData);

        androidData = new DataClass("Hip hop", R.string.hiphop,  R.drawable.img_hiphop);
        dataList.add(androidData);

        androidData = new DataClass("Lirico", R.string.lirico, R.drawable.img_lirico);
        dataList.add(androidData);

        androidData = new DataClass("Contemporaneo", R.string.contemporaneo,  R.drawable.img_contemporaneo);
        dataList.add(androidData);

        androidData = new DataClass("Tahitiano", R.string.tahitiano,  R.drawable.img_tahitiano);
        dataList.add(androidData);

        androidData = new DataClass("Ritmos latinos", R.string.Ritmoslatinos,  R.drawable.img_ritmoslatinos);
        dataList.add(androidData);

        adapter = new MyAdapter(BailesActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text){
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Baile no encontrado", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
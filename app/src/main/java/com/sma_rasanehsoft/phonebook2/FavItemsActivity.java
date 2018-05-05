package com.sma_rasanehsoft.phonebook2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import Adaptors.FavItemAdaptor;
import Models.PersonsLocal;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class FavItemsActivity extends ActivityEnhanced {
    private FavItemAdaptor adapter;
    private RecyclerView rvFavItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_items);

        if (PersonsLocal.all().size() == 0) {
            Toast.makeText(App.CONTEXT,getString(R.string.no_news_saved),Toast.LENGTH_LONG).show();
        }

        rvFavItem = (RecyclerView) findViewById(R.id.rvFavItem);
        adapter = new FavItemAdaptor(PersonsLocal.all());
        rvFavItem.setLayoutManager(new LinearLayoutManager(App.ACTIVITY));
        rvFavItem.setHasFixedSize(true);
        rvFavItem.setAdapter(adapter);
        rvFavItem.setItemAnimator(new LandingAnimator());
        adapter.notifyDataSetChanged();
        rvFavItem.setItemViewCacheSize(20);
        rvFavItem.setDrawingCacheEnabled(true);
        rvFavItem.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        findViewById(R.id.imgback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.rvPersons.removeAllViews();
    }

}

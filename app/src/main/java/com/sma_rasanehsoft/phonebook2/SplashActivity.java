package com.sma_rasanehsoft.phonebook2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Adaptors.AdapterPerson;
import Async.AsyncRead;
import Models.Persons;
import StructPerson.StructPerson;
import web.Commands;


public class SplashActivity extends ActivityEnhanced {
    LinearLayout disconnect;
    ImageView imageView3;
    private AdapterPerson adapter;
    private SwipeRefreshLayout swpReload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        disconnect = (LinearLayout) findViewById(R.id.desConnect);
        imageView3 = (ImageView) findViewById(R.id.imageView3);

        findViewById(R.id.connectretry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnect.setVisibility(View.GONE);
                recieve();
            }

        });
        recieve();
//        adapter = new AdapterPerson(Persons.all());
    }
    public void recieve() {

        if (OnlineCheck.isOnline()) {
            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (Commands.read(0).size() > 0) {
                                new AsyncRead().setOnDataReceivedListener(new AsyncRead.OnDataReceivedListener() {
                                    @Override
                                    public void OnDataReceived(ArrayList<StructPerson> result) {
                                        Persons.clear();
                                        for (StructPerson person : result) {
                                            Persons.save(person);
                                        }
                                        adapter.persons = Persons.all();
                                    }

                                })
                                        .execute(0);
                            }
                        }
                    });
                }
            }, 1, 1000);
            App.HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(App.CONTEXT, MainActivity.class);
                    startActivity(intent);
                    timer.cancel();
                    finish();


                }
            }, 2000);

        }
        else {
            if (Persons.all().size() > 0) {
                App.HANDLER.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(App.CONTEXT, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }, 1000);
            } else {

                disconnect.setVisibility(View.VISIBLE);
            }

        }
//


    }
}










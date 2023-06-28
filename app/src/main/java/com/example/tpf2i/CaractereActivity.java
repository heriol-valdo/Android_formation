package com.example.tpf2i;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CaractereActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private PersonnageService personnageService;


    private static final String BASE_URL = "https://api.disneyapi.dev/";

    private Thread thread;

    private RecyclerView recyclerViewPersonnage;
    private CaractereAdapter caractereAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Call<CaractereRoot> personnageRootCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);

        recyclerViewPersonnage = findViewById(R.id.personnageRecycler);

        layoutManager = new GridLayoutManager(getApplicationContext(),2);

        // Client Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        personnageService = retrofit.create(PersonnageService.class);


        personnageRootCall = personnageService.getPersonnageList();


        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Response<CaractereRoot> response = personnageRootCall.execute();

                    if(response.isSuccessful()){
                        List<Caractere> caractereList = response.body().getPersonnageList();

                        //Log.i("xxxxx",String.valueOf(personnageList.size()));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                caractereAdapter = new CaractereAdapter(
                                        getApplicationContext(),
                                        caractereList
                                );

                                // Connecter le recyclerView à l'adapter
                                recyclerViewPersonnage.setAdapter(caractereAdapter);
                                recyclerViewPersonnage.setLayoutManager(layoutManager);
                                recyclerViewPersonnage.setHasFixedSize(true);
                            }
                        });
                    }

                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

    }
}
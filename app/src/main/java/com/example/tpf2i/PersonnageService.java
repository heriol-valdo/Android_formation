package com.example.tpf2i;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonnageService {

    @GET("character")
    Call<CaractereRoot> getPersonnageList();
}

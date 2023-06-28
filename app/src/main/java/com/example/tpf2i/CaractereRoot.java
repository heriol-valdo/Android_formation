package com.example.tpf2i;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CaractereRoot {

    @SerializedName("data")
    private List<Caractere> caractereList;

    public CaractereRoot(List<Caractere> caractereList) {
        this.caractereList = caractereList;
    }

    public List<Caractere> getPersonnageList() {
        return caractereList;
    }

    public void setPersonnageList(List<Caractere> caractereList) {
        this.caractereList = caractereList;
    }
}

package com.example.choejun_yeong.blocker_android.DataModel;

import com.google.gson.annotations.SerializedName;

public class Election {

    @SerializedName("id")
    private int election_id;

    @SerializedName("name")
    private String election_name;


    public int getElection_id() {
        return election_id;
    }



    public void setElection_id(int election_id) {
        this.election_id = election_id;
    }

    public String getElection_name() {
        return election_name;
    }

    public void setElection_name(String election_name) {
        this.election_name = election_name;
    }
}

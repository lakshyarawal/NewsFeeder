package com.example.lakshya.myapplication.Network;

import java.util.ArrayList;

/**
 * Created by LAKSHYA on 9/3/2017.
 */

public class SourceResponse {
    private String status;
    private ArrayList<Source> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public void setSources(ArrayList<Source> sources) {
        this.sources = sources;
    }
}

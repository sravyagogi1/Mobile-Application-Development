package com.sravyagogineni.lab05;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LifecycleData {
    int onCreate = 0;
    int onStart = 0;
    int onResume = 0;
    int onPause = 0;
    int onStop = 0;
    int onRestart = 0;
    int onDestroy = 0;
    String duration;

    @Override
    public String toString(){
        return duration + "\n" +
                "onCreate \t\t\t\t" + onCreate + "\n" +
                "onStart \t\t\t\t" + onStart + "\n" +
                "onResume \t\t\t\t" + onResume + "\n" +
                "onPause \t\t\t\t" + onPause + "\n" +
                "onStop \t\t\t\t" + onStop + "\n" +
                "onRestart \t\t\t\t" + onRestart + "\n" +
                "onDestroy \t\t\t\t" + onDestroy + "\n";
    }

    String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this, LifecycleData.class);
    }

    static LifecycleData parseJSON(String fromSharedPreferences){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(fromSharedPreferences, LifecycleData.class);
    }

    void updateEvent(String eventName){
        switch(eventName){
            case "onCreate": onCreate++; break;
            case "onStart": onStart++; break;
            case "onResume": onResume++; break;
            case "onPause": onPause++; break;
            case "onStop": onStop++; break;
            case "onRestart": onRestart++; break;
            case "onDestroy": onDestroy++; break;
            default: break;
        }
    }
}

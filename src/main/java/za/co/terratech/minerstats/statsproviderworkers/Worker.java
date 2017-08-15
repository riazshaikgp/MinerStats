/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats.statsproviderworkers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import za.co.terratech.minerstats.Speed;

/**
 *
 * @author riazs
 */
/*
14:23:51,951 INFO  [stdout] (pool-5-thread-1) warlock
14:23:51,952 INFO  [stdout] (pool-5-thread-1) {a=1.35}
14:23:51,952 INFO  [stdout] (pool-5-thread-1) 38.0
14:23:51,953 INFO  [stdout] (pool-5-thread-1) 1.0
14:23:51,953 INFO  [stdout] (pool-5-thread-1) 2
14:23:51,961 INFO  [stdout] (pool-5-thread-1) 0.0
14:23:51,962 INFO  [stdout] (pool-5-thread-1) 27.0
*/
public class Worker {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("speeds")
    @Expose
    private Speed speeds;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("xnsub")
    @Expose
    private String xnsub;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;
    @SerializedName("location")
    @Expose
    private String location;
    
    @SerializedName("algoName")
    @Expose
    private String algoName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speed getSpeeds() {
        return speeds;
    }

    public void setSpeeds(Speed speeds) {
        this.speeds = speeds;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getXnsub() {
        return xnsub;
    }

    public void setXnsub(String xnsub) {
        this.xnsub = xnsub;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }
    
    
}

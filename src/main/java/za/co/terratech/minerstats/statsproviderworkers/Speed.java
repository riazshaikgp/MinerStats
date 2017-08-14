/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats.statsproviderworkers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author riazs
 */
public class Speed {
    @SerializedName("a")
    @Expose
    private String a;
    @SerializedName("rs")
    @Expose
    private String rs;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }
    
}

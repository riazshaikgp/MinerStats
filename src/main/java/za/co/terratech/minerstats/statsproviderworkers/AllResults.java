/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats.statsproviderworkers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author riazs
 */
public class AllResults {
    @SerializedName("results")
    @Expose
    private List<Worker> results;

    public List<Worker> getResults() {
        if (results == null){
            results = new ArrayList();
        }
        return results;
    }

    public void setResults(List<Worker> results) {
        this.results = results;
    }
    
    
}

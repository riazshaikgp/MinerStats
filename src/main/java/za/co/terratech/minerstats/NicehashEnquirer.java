/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import za.co.terratech.minerstats.algorithms.Algorithms;
import za.co.terratech.minerstats.algorithms.Simplemultialgo;
import za.co.terratech.minerstats.statsproviderworkers.StatsProviderWorkers;

/**
 *
 * @author riazs
 */
public class NicehashEnquirer extends Thread {
    private String BTCADDRESS;
    private static final String NICEHASH = "https://api.nicehash.com/api";
    private static final String ALGOS = "?method=simplemultialgo.info";
    private static final String WORKERS = "?method=stats.provider.workers";
    private static final String STATSEX = "?method=stats.provider.ex";
    private static final String STATS = "?method=stats.provider";
    private Map<Integer, Simplemultialgo> algo;
    private za.co.terratech.minerstats.statsproviderworkers.Result workers;
    public NicehashEnquirer(String btcAddress) {
        this.BTCADDRESS = "&addr="+btcAddress;
    }

    @Override
    public void run() {
        Client client = Client.create();
        String json = MediaType.APPLICATION_JSON;
        WebResource resource = client.resource(NICEHASH+ALGOS);
        resource.addFilter(new LoggingFilter(Logger.getLogger(NicehashEnquirer.class.getSimpleName())));
        String stringAlgo = resource.accept(json).get(String.class);
        Gson gson = new Gson();
        algo = new HashMap();
        gson.fromJson(stringAlgo, Algorithms.class).getResult().getSimplemultialgo().forEach(al -> {
            algo.put(al.getAlgo(), al);
        });
        while(true){
            try {
                
                
                
                resource = client.resource(NICEHASH+WORKERS+BTCADDRESS+"&algo=20");
                String stringWorkers = resource.accept(json).get(String.class);
                workers = gson.fromJson(stringWorkers, StatsProviderWorkers.class).getResult();
                System.out.println(workers);
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NicehashEnquirer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}

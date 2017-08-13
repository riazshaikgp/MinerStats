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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import za.co.terratech.minerstats.algorithms.Algorithms;
import za.co.terratech.minerstats.algorithms.Simplemultialgo;
import za.co.terratech.minerstats.statsprovider.Stat;
import za.co.terratech.minerstats.statsprovider.StatsProvider;
import za.co.terratech.minerstats.statsproviderworkers.Result;
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
    private Map<Integer, Simplemultialgo> algos;
    private Algorithms algorithms;
    private List<za.co.terratech.minerstats.statsproviderworkers.Result> workerStats;
    private za.co.terratech.minerstats.statsprovider.Result stats;

    public NicehashEnquirer(String btcAddress) {
        this.BTCADDRESS = "&addr=" + btcAddress;
    }

    @Override
    public void run() {
        Client client = Client.create();
        String json = MediaType.APPLICATION_JSON;
        WebResource resource = client.resource(NICEHASH + ALGOS);
        resource.addFilter(new LoggingFilter(Logger.getLogger(NicehashEnquirer.class.getSimpleName())));
        String stringAlgo = resource.accept(json).get(String.class);
        Gson gson = new Gson();
        algos = new HashMap();
        algorithms = gson.fromJson(stringAlgo, Algorithms.class);
        gson.fromJson(stringAlgo, Algorithms.class).getResult().getSimplemultialgo().forEach(al -> {
            algos.put(al.getAlgo(), al);
        });
        while (true) {
            try {
                resource = client.resource(NICEHASH + STATS + BTCADDRESS);
                String jsonStats = resource.accept(json).get(String.class);
                stats = gson.fromJson(jsonStats, StatsProvider.class).getResult();
                List<Stat> miningStats = stats.getStats();
                workerStats = new ArrayList();
                for (Stat stat : miningStats) {
                    if (!stat.getAcceptedSpeed().equals("0.00000000")) {
                        resource = client.resource(NICEHASH + WORKERS + BTCADDRESS + "&algo=" + stat.getAlgo());
                        String stringWorkers = resource.accept(json).get(String.class);
                        workerStats.add(gson.fromJson(stringWorkers, StatsProviderWorkers.class).getResult());
                    }
                }

                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NicehashEnquirer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String getBTCADDRESS() {
        return BTCADDRESS;
    }

    public void setBTCADDRESS(String BTCADDRESS) {
        this.BTCADDRESS = BTCADDRESS;
    }

    public Map<Integer, Simplemultialgo> getAlgos() {
        return algos;
    }

    public void setAlgos(Map<Integer, Simplemultialgo> algos) {
        this.algos = algos;
    }

    public List<Result> getWorkerStats() {
        return workerStats;
    }

    public void setWorkerStats(List<Result> workerStats) {
        this.workerStats = workerStats;
    }

    public za.co.terratech.minerstats.statsprovider.Result getStats() {
        return stats;
    }

    public void setStats(za.co.terratech.minerstats.statsprovider.Result stats) {
        this.stats = stats;
    }

    public Algorithms getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(Algorithms algorithms) {
        this.algorithms = algorithms;
    }

    
    
    public static void main(String[] args) {
        NicehashEnquirer enquirer = new NicehashEnquirer("1isZ1cMATUgbH9iWSVMBPgr6SaC6aKoT9");
        enquirer.run();
    }

}

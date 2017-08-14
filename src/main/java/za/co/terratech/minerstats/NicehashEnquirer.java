/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
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
import za.co.terratech.minerstats.statsproviderworkers.AllResults;
import za.co.terratech.minerstats.statsproviderworkers.Result;
import za.co.terratech.minerstats.statsproviderworkers.Speed;
import za.co.terratech.minerstats.statsproviderworkers.StatsProviderWorkers;
import za.co.terratech.minerstats.statsproviderworkers.Worker;

/**
 *
 * @author riazs
 */
public class NicehashEnquirer extends Thread {

    private String btcAddress;
    private static final String NICEHASH = "https://api.nicehash.com/api";
    private static final String ALGOS = "?method=simplemultialgo.info";
    private static final String WORKERS = "?method=stats.provider.workers";
    private static final String STATSEX = "?method=stats.provider.ex";
    private static final String STATS = "?method=stats.provider";
    private Map<Integer, Simplemultialgo> algos;
    private Algorithms algorithms;
    private AllResults allWorkerResults;
    private za.co.terratech.minerstats.statsprovider.Result stats;

    public NicehashEnquirer(String btcAddress) {
        this.btcAddress = "&addr=" + btcAddress;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Client client = Client.create();
                String json = MediaType.APPLICATION_JSON;
                WebResource resource = client.resource(NICEHASH + ALGOS);
                String stringAlgo = resource.accept(json).get(String.class);
                Gson gson = new Gson();
                Map<Integer, Simplemultialgo> algo = new HashMap();
                Algorithms algorithm = gson.fromJson(stringAlgo, Algorithms.class);
                gson.fromJson(stringAlgo, Algorithms.class).getResult().getSimplemultialgo().forEach(al -> {
                    algo.put(al.getAlgo(), al);
                });
                algorithms = algorithm;
                algos = algo;
                resource = client.resource(NICEHASH + STATS + btcAddress);
                String jsonStats = resource.accept(json).get(String.class);
                stats = gson.fromJson(jsonStats, StatsProvider.class).getResult();
                List<Stat> miningStats = stats.getStats();
                AllResults workerResults = new AllResults();
                for (Stat stat : miningStats) {
                    if (!stat.getAcceptedSpeed().equals("0.00000000")) {
                        resource = client.resource(NICEHASH + WORKERS + btcAddress + "&algo=" + stat.getAlgo());
                        String stringWorkers = resource.accept(json).get(String.class);
                        StatsProviderWorkers workerstats = gson.fromJson(stringWorkers, StatsProviderWorkers.class);
                        workerResults.getResults().addAll(createActualWorkers(workerstats.getResult()).getActualWorkers());
                    }
                }
                allWorkerResults = workerResults;
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NicehashEnquirer.class.getName()).log(Level.INFO, "Thread Interrupted, shutting down");
                break;
            }
        }

    }

    private Result createActualWorkers(Result result) {
        result.setAlgoName(algos.get(result.getAlgo()).getName());
        for (List<Object> workerProps : result.getWorkers()) {
            Worker worker = new Worker();
            worker.setName((String) workerProps.get(0));
            worker.setSpeeds(createSpeedObject((LinkedTreeMap<String, String>) workerProps.get(1)));
            worker.setTime(String.valueOf(workerProps.get(2)));
            worker.setXnsub(String.valueOf(workerProps.get(3)));
            worker.setDifficulty(String.valueOf(workerProps.get(4)));
            worker.setLocation(String.valueOf(workerProps.get(5)));
            worker.setAlgoName(result.getAlgoName());
            result.getActualWorkers().add(worker);
        }
        return result;
    }

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
    }

    public Map<Integer, Simplemultialgo> getAlgos() {
        return algos;
    }

    public void setAlgos(Map<Integer, Simplemultialgo> algos) {
        this.algos = algos;
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

    public AllResults getAllWorkerResults() {
        return allWorkerResults;
    }

    public void setAllWorkerResults(AllResults allWorkerResults) {
        this.allWorkerResults = allWorkerResults;
    }

    public static void main(String[] args) {
        NicehashEnquirer enquirer = new NicehashEnquirer("1isZ1cMATUgbH9iWSVMBPgr6SaC6aKoT9");
        enquirer.run();
    }

    private Speed createSpeedObject(LinkedTreeMap<String, String> obj) {
        Speed speed = new Speed();
        speed.setA(obj.get("a") != null ? obj.get("a") : "0");
        speed.setRs(obj.get("rs") != null ? obj.get("rs") : "0");
        return speed;
    }

}

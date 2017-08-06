/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riazs
 */
public class MinerEquirer extends Thread {

    Logger log = Logger.getLogger(MinerEquirer.class.getName());

    public MinerEquirer(int id, String host, int port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }

    private int id;
    private String host;
    private int port;
    private MinerStatsResponse minerStats;

    @Override
    public void run() {
        log.log(Level.INFO, "Started MinerEquirer thread for {0}", host);
        while (true) {
            try {
                minerStats = enquire();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MinerEquirer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public MinerStatsResponse getMinerStats() {
        return minerStats;
    }

    public MinerStatsResponse enquire() {
        MinerStatsResponse responseObject;
        try {
            String request = "{\"id\":" + id + ",\"jsonrpc\":\"2.0\",\"method\":\"miner_getstat1\"}";
            String response ="";
            try (Socket clientSocket = new Socket()) {
                clientSocket.connect(new InetSocketAddress(host, port), 1000);
                clientSocket.setSoTimeout(1000);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToServer.writeBytes(request + '\n');
                response = inFromServer.readLine();
                Gson gson = new Gson();
                responseObject = gson.fromJson(response, MinerStatsResponse.class);
                responseObject.setHost(host);
                responseObject.setPort(String.valueOf(port));
                Result result = createMinerResult(responseObject.getResult());
                responseObject.setMinerResult(result);
            }
            return responseObject;
        } catch (SocketTimeoutException ex) {
            ex.printStackTrace();
            responseObject = new MinerStatsResponse(0, ex.getMessage());
            return responseObject;
        } catch (IOException ex) {
            ex.printStackTrace();
            responseObject = new MinerStatsResponse(0, ex.getMessage());
            return responseObject;
        }
    }

    public static void main(String argv[]) throws InterruptedException {
        Gson gson = new Gson();
        while (true) {
            System.out.println(gson.toJson(new MinerEquirer(0, "192.168.1.2", 3333).enquire()));
            System.out.println(gson.toJson(new MinerEquirer(1, "192.168.1.3", 3333).enquire()));
            Thread.sleep(1000);
        }
    }

    private Result createMinerResult(List<String> res) {
        try {
            Result result = new Result();
            result.setClaymoreVersion(res.get(0));
            result.setUptime(String.format("%dh:%02dmin/s", Integer.parseInt(res.get(1)) / 60, Integer.parseInt(res.get(1)) % 60));
            String hash = String.valueOf(Double.parseDouble(res.get(2).substring(0, res.get(2).indexOf(";"))) / 1000);
            String total = res.get(2).substring(res.get(2).indexOf(";") + 1, res.get(2).lastIndexOf(";"));
            String rejects = res.get(2).substring(res.get(2).lastIndexOf(";") + 1);
            result.setTotalEthHashrate(hash);
            result.setTotalEthShares(total);
            result.setTotalEthRejects(rejects);
            result.setIndividualEthGpuHashrates(res.get(3));
            hash = String.valueOf(Double.parseDouble(res.get(4).substring(0, res.get(4).indexOf(";"))) / 1000);
            total = res.get(4).substring(res.get(4).indexOf(";") + 1, res.get(4).lastIndexOf(";"));
            rejects = res.get(4).substring(res.get(4).lastIndexOf(";") + 1);
            result.setTotalDecHashrate(hash);
            result.setTotalDecShares(total);
            result.setTotalDecRejects(rejects);
            result.setIndividualDecGpuHashrates(res.get(5));
            result.setGpuTempFanSpeed(res.get(6));
            result.setEthPool(res.get(7).substring(0, res.get(7).indexOf(";")));
            result.setDecPool(res.get(7).substring(res.get(7).indexOf(";") + 1));
            String[] strings = res.get(8).split(";");
            result.setEthInvalidShares(strings[0]);
            result.setEthPoolSwitches(strings[1]);
            result.setDecInvalidShares(strings[2]);
            result.setDecPoolSwitches(strings[3]);
            return result;
        } catch (StringIndexOutOfBoundsException ex) {
            return createdEthOnlyMinerResult(res);
        }
    }

    private Result createdEthOnlyMinerResult(List<String> res) {
        Result result = new Result();
        result.setClaymoreVersion(res.get(0));
        result.setUptime(String.format("%dh:%02dmin/s", Integer.parseInt(res.get(1)) / 60, Integer.parseInt(res.get(1)) % 60));
        String hash = String.valueOf(Double.parseDouble(res.get(2).substring(0, res.get(2).indexOf(";"))) / 1000);
        String total = res.get(2).substring(res.get(2).indexOf(";") + 1, res.get(2).lastIndexOf(";"));
        String rejects = res.get(2).substring(res.get(2).lastIndexOf(";") + 1);
        result.setTotalEthHashrate(hash);
        result.setTotalEthShares(total);
        result.setTotalEthRejects(rejects);

        result.setIndividualEthGpuHashrates(res.get(3));

        hash = String.valueOf(Double.parseDouble(res.get(4).substring(0, res.get(4).indexOf(";"))) / 1000);
        total = res.get(4).substring(res.get(4).indexOf(";") + 1, res.get(4).lastIndexOf(";"));
        rejects = res.get(4).substring(res.get(4).lastIndexOf(";") + 1);
        result.setTotalDecHashrate(hash);
        result.setTotalDecShares(total);
        result.setTotalDecRejects(rejects);

        result.setIndividualDecGpuHashrates(res.get(5));

        result.setGpuTempFanSpeed(res.get(6));
        result.setEthPool(res.get(7));
        result.setDecPool("");
        String[] strings = res.get(8).split(";");
        result.setEthInvalidShares(strings[0]);
        result.setEthPoolSwitches(strings[1]);
        result.setDecInvalidShares(strings[2]);
        result.setDecPoolSwitches(strings[3]);
        return result;
    }

}
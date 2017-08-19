/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author riazs
 */
@Path("/MinerService")
public class MinerService {

    private static final CacheControl cc;

    static {
        cc = new CacheControl();
        cc.setMaxAge(0);
        cc.setPrivate(true);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConfig() {
        ResponseBuilder builder = Response.ok(ContextListener.getConfig());
        builder.cacheControl(cc);
        return builder.build();
    }

    @GET
    @Path("/getMinerParams/{s}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMinerParams(@PathParam("s") String name) {
        for (Miner miner : ContextListener.getConfig().getMiners()) {
            if (miner.getName().equals(name)) {
                ResponseBuilder builder = Response.ok(miner);
                builder.cacheControl(cc);
                return builder.build();
            }
        }
        return null;
    }

    @GET
    @Path("/getMinerStats/{s}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMinerStats(@PathParam("s") String name) {
        MinerStatsResponse response = ContextListener.getMINERS().get(name).getMinerStats();
        ResponseBuilder builder = Response.ok(response);
        builder.cacheControl(cc);
        return builder.build();
    }

    @GET
    @Path("/restartMiner/{s}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response restartMiner(@PathParam("s") String id) {
        List<Miner> miners = ContextListener.getConfig().getMiners();
        for (Miner miner : miners) {
            if (miner.getId() == Integer.parseInt(id)) {
                String request = "{\"id\":" + id + ",\"jsonrpc\":\"2.0\",\"method\":\"miner_restart\"}";
                try (Socket clientSocket = new Socket()) {
                    clientSocket.connect(new InetSocketAddress(miner.getHost(), miner.getPort()), 60000);
                    clientSocket.setSoTimeout(60000);
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    outToServer.writeBytes(request + '\n');
                } catch (IOException ex) {
                    Logger.getLogger(MinerService.class.getName()).log(Level.SEVERE, null, ex);
                }
                ResponseBuilder builder = Response.ok(new Ok());
                builder.cacheControl(cc);
                return builder.build();
            }
        }
        ResponseBuilder builder = Response.status(404);
        builder.cacheControl(cc);
        return builder.build();
    }

    @GET
    @Path("/rebootMiner/{s}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rebootMiner(@PathParam("s") String id) {
        List<Miner> miners = ContextListener.getConfig().getMiners();
        for (Miner miner : miners) {
            if (miner.getId() == Integer.parseInt(id)) {
                String request = "{\"id\":" + id + ",\"jsonrpc\":\"2.0\",\"method\":\"miner_reboot\"}";
                try (Socket clientSocket = new Socket()) {
                    clientSocket.connect(new InetSocketAddress(miner.getHost(), miner.getPort()), 60000);
                    clientSocket.setSoTimeout(60000);
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    outToServer.writeBytes(request + '\n');
                } catch (IOException ex) {
                    Logger.getLogger(MinerService.class.getName()).log(Level.SEVERE, null, ex);
                }
                ResponseBuilder builder = Response.ok(new Ok());
                builder.cacheControl(cc);
                return builder.build();
            }
        }
        ResponseBuilder builder = Response.status(404);
        builder.cacheControl(cc);
        return builder.build();
    }

}

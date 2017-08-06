/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats;

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
        MinerStatsResponse response = ContextListener.getMiners().get(name).getMinerStats();
        ResponseBuilder builder = Response.ok(response);
        builder.cacheControl(cc);
        return builder.build();
    }

}

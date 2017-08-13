/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.terratech.minerstats;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import za.co.terratech.minerstats.algorithms.Simplemultialgo;

/**
 *
 * @author riazs
 */
@Path("/NicehashService")
public class NicehashService {
    
    private static final CacheControl cc;

    static {
        cc = new CacheControl();
        cc.setMaxAge(0);
        cc.setPrivate(true);
    }
    
    @GET
    @Path("/getAlgos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlgos(){
        ResponseBuilder builder = Response.ok(ContextListener.getNhEnquirer().getAlgorithms());
        builder.cacheControl(cc);
        return builder.build();
    }
}

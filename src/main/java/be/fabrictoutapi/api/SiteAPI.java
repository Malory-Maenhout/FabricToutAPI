package be.fabrictoutapi.api;

import java.sql.Connection;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import be.fabrictoutapi.dao.*;
import be.fabrictoutapi.javabeans.*;

@Path("/site")
public class SiteAPI {

	private Connection conn;
	private Response response;

	//All
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSites() {
		conn = APIRestConnection.getInstance();
	    ArrayList<Site> sites = new SiteDAO(conn).findall();
	    if (!sites.isEmpty())
			response = Response.status(Response.Status.OK).entity(sites).build();
	    else
	        response = Response.status(Response.Status.NO_CONTENT).build();
	
	    return response;
	}
	
	// /id
	@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSite(@PathParam("id") int id) {		
		conn = APIRestConnection.getInstance();
		Site site = new SiteDAO(conn).find(id);

        if (site != null)
    		response = Response.status(Response.Status.OK).entity(site).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).entity(null).build();

        return response;
    }
}

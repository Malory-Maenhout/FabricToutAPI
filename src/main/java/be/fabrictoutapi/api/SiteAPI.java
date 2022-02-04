package be.fabrictoutapi.api;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import be.fabrictoutapi.dao.*;
import be.fabrictoutapi.javabeans.*;

@Path("/site")
public class SiteAPI {
	//Attributes/Variables
	private Connection conn;
	private Response response;

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
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSite(
            @FormParam("city") String city,
            @FormParam("country") String country
            ) {
        List<Site> sites = new SiteDAO(conn).findall();
        
        boolean verif = true;
        
        for(Site s : sites) {
            if(s.getCity().toLowerCase().equals(city.toLowerCase())) {
                verif = false;
                break;
            }
        }
        
        if(verif) {
            Site site = new Site(city, country);
            
            conn = APIRestConnection.getInstance();
           
            boolean insert = new SiteDAO(conn).create(site);
            
            if (insert)
                response = Response.status(Response.Status.CREATED).entity(true).build();
            else
                response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
        }
        else 
        {
            response = Response.status(Response.Status.CONFLICT).entity(false).build();
        }
        
        return response;
    }
}
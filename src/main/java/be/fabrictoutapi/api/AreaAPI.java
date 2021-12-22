package be.fabrictoutapi.api;

import java.sql.Connection;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import be.fabrictoutapi.dao.APIRestConnection;
import be.fabrictoutapi.dao.AreaDAO;
import be.fabrictoutapi.javabeans.Area;

@Path("/area")
public class AreaAPI {
	private Connection conn;
	private Response response;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAreas() {
		conn = APIRestConnection.getInstance();
	    ArrayList<Area> areas = new AreaDAO(conn).findall();
	    if (!areas.isEmpty())
			response = Response.status(Response.Status.OK).entity(areas).build();
	    else
	        response = Response.status(Response.Status.NO_CONTENT).build();
	
	    return response;
	}
	
	// /id
	@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArea(@PathParam("id") int id) {		
		conn = APIRestConnection.getInstance();
		Area area = new AreaDAO(conn).find(id);

        if (area != null)
    		response = Response.status(Response.Status.OK).entity(area).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).entity(null).build();

        return response;
    }
}

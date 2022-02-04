package be.fabrictoutapi.api;

import java.sql.Connection;
import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	//Attributes/Variables
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
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createArea(
            @FormParam("letter") char letter,
            @FormParam("color") String color,
            @FormParam("description") String description,
            @FormParam("id_site") int id_site) {
        Area area = new Area(letter, color, description);
        
        conn = APIRestConnection.getInstance();
        
        boolean insert = new AreaDAO(conn).create(id_site, area);
        
        if (insert)
            response = Response.status(Response.Status.CREATED).entity(true).build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
        
        return response;
    }
}
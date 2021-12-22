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
import be.fabrictoutapi.dao.MaintenanceDAO;
import be.fabrictoutapi.javabeans.Maintenance;

@Path("/maintenance")
public class MaintenanceAPI {
	private Connection conn;
	private Response response;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAreas() {
		conn = APIRestConnection.getInstance();
	    ArrayList<Maintenance> maintenances = new MaintenanceDAO(conn).findall();
	    if (!maintenances.isEmpty())
			response = Response.status(Response.Status.OK).entity(maintenances).build();
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
		Maintenance maintenance = new MaintenanceDAO(conn).find(id);

        if (maintenance != null)
    		response = Response.status(Response.Status.OK).entity(maintenance).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).entity(null).build();

        return response;
    }
}

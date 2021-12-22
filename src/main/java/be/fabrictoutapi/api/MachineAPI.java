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
import be.fabrictoutapi.dao.MachineDAO;
import be.fabrictoutapi.javabeans.Machine;

@Path("/machine")
public class MachineAPI {
	private Connection conn;
	private Response response;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllMachines() {
		conn = APIRestConnection.getInstance();
	    ArrayList<Machine> machines = new MachineDAO(conn).findall();
	    if (!machines.isEmpty())
			response = Response.status(Response.Status.OK).entity(machines).build();
	    else
	        response = Response.status(Response.Status.NO_CONTENT).build();
	
	    return response;
	}
	
	// /id
	@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMachine(@PathParam("id") int id) {		
		conn = APIRestConnection.getInstance();
		Machine machine = new MachineDAO(conn).find(id);

        if (machine != null)
    		response = Response.status(Response.Status.OK).entity(machine).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).entity(null).build();

        return response;
    }
}

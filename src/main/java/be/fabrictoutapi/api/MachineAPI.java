package be.fabrictoutapi.api;

import java.sql.Connection;
import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import be.fabrictoutapi.dao.APIRestConnection;
import be.fabrictoutapi.dao.MachineDAO;
import be.fabrictoutapi.javabeans.*;

@Path("/machine")
public class MachineAPI {
	//Attributes/Variables
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
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMachine(
            @FormParam("name") String name,
            @FormParam("size") String size,
            @FormParam("status") String status,
            @FormParam("type") String type,
            @FormParam("serialNumber") String serialNumber,
            @FormParam("replace") boolean replace,
            @FormParam("areas") String areas) {	
        Machine machine = new Machine(name, size, status, replace, serialNumber, type);
        
        String[] are = areas.split(",");
       
        for(int i = 0; i < are.length; i++) {
            Area a = new Area();
            a.setId(Integer.valueOf(are[i]));
            machine.getAreaList().add(a);
        }
        
        conn = APIRestConnection.getInstance();
        
        boolean insert = new MachineDAO(conn).create(machine);
        
        if (insert)
            response = Response.status(Response.Status.CREATED).entity(true).build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
            
        return response;
    }
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMachine(
            @FormParam("id") int id,
            @FormParam("name") String name,
            @FormParam("size") String size,
            @FormParam("status") String status,
            @FormParam("type") String type,
            @FormParam("serialNumber") String serialNumber,
            @FormParam("replace") boolean replace) {      
        Machine machine = new Machine(id, name, size, status, replace, serialNumber, type);
        
        conn = APIRestConnection.getInstance();
        
        boolean insert = new MachineDAO(conn).update(machine);
        
        if (insert)
            response = Response.status(Response.Status.OK).entity(true).build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
        return response;
    }
}
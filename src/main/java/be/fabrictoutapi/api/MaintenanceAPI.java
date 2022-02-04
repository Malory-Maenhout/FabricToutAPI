package be.fabrictoutapi.api;

import java.sql.Connection;
import java.sql.Date;
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
import be.fabrictoutapi.dao.*;
import be.fabrictoutapi.enums.StatusEnum;
import be.fabrictoutapi.javabeans.*;

@Path("/maintenance")
public class MaintenanceAPI {
	//Attributes/Variables
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
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMaintenance(
            @FormParam("date") Date date,
            @FormParam("duration") int duration,
            @FormParam("status") String status,
            @FormParam("id_manager") int manager_id,
            @FormParam("id_machine") int machine_id,
            @FormParam("workers") String workers) {
        Maintenance m = new Maintenance();
        m.setDate(date);
        m.setDuration(duration);
        
        switch(status) {
			case "Do" -> m.setStatus(StatusEnum.Do);
			case "ToDo" -> m.setStatus(StatusEnum.ToDo);
			case "InValidate" -> m.setStatus(StatusEnum.InValidate);
			case "Validate" -> m.setStatus(StatusEnum.Validate);
			default -> m.setStatus(StatusEnum.Do);
        };
        
        Machine machine = new Machine();
        machine.setId(machine_id);
        m.setMachine(machine);
        
        String[] are = workers.split(",");
       
        for(int i = 0; i < are.length; i++) {
            Worker w = new Worker();
            w.setId(Integer.valueOf(are[i]));
            m.getWorkerList().add(w);
        }
        
        conn = APIRestConnection.getInstance();
       
        boolean insert = new MaintenanceDAO(conn).create(manager_id, m);
        
        if (insert)
            response = Response.status(Response.Status.CREATED).entity(true).build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
            
        return response;
    }
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMaintenance(
    		@FormParam("id") int id,
            @FormParam("date") Date date,
            @FormParam("duration") int duration,
            @FormParam("status") String status
            ) {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(id);
        maintenance.setDate(date);
        maintenance.setDuration(duration);
        
        switch(status) {
			case "Do" -> maintenance.setStatus(StatusEnum.Do);
			case "ToDo" -> maintenance.setStatus(StatusEnum.ToDo);
			case "InValidate" -> maintenance.setStatus(StatusEnum.InValidate);
			case "Validate" -> maintenance.setStatus(StatusEnum.Validate);
			default -> maintenance.setStatus(StatusEnum.Do);
		};
        
		conn = APIRestConnection.getInstance();
       
		boolean insert = new MaintenanceDAO(conn).update(maintenance);
        
		if (insert)
            response = Response.status(Response.Status.OK).entity(true).build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
        return response;
    }
}
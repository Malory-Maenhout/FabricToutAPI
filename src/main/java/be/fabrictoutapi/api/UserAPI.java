package be.fabrictoutapi.api;

import java.sql.Connection;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import be.fabrictoutapi.javabeans.*;
import be.fabrictoutapi.dao.*;

@Path("/user")
public class UserAPI {
	private Connection conn;
	private Response response;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		conn = APIRestConnection.getInstance();
	    ArrayList<User> users = new UserDAO(conn).findall();
	    if (!users.isEmpty())
			response = Response.status(Response.Status.OK).entity(users).build();
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
		User user = new UserDAO(conn).find(id);

        if (user != null)
    		response = Response.status(Response.Status.OK).entity(user).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).entity(null).build();

        return response;
    }
	
	@GET
    @Path("{personnelNumber}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(@PathParam("personnelNumber") String personnelNumber, @PathParam("password") String password) {		
		conn = APIRestConnection.getInstance();
        User user = new UserDAO(conn).find(personnelNumber, password);

        if (user != null)
    		response = Response.status(Response.Status.OK).entity(user).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).build();

        return response;
    }
	
}
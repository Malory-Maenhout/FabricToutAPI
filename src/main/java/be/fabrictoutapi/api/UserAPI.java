package be.fabrictoutapi.api;

import java.sql.Connection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import be.fabrictoutapi.dao.APIRestConnection;
import be.fabrictoutapi.dao.UserDAO;
import be.fabrictoutapi.javabeans.User;

@Path("/user")
public class UserAPI {

	private Connection conn;
	private Response response;
	
	@GET
	@Path("{personnelNumber}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("personnelNumber") String personnelNumber, @PathParam("password") String password ) {
		conn = APIRestConnection.getInstance();
		User user = new UserDAO(conn).find(personnelNumber, password);
		
		if (user != null)
            response = Response.status(Response.Status.OK).entity(user).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).entity(null).build();
		
		return response;
	}
}

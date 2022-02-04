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
import be.fabrictoutapi.javabeans.*;
import be.fabrictoutapi.dao.*;																				

@Path("/user")
public class UserAPI {
	//Attributes/Variables
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
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(
            @FormParam("id_site") int id_site,
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("address") String address,
            @FormParam("dateOfBirth") Date dateOfBirth,
            @FormParam("sexe") char sexe,
            @FormParam("city") String city,
            @FormParam("postalCode") int postalCode,
            @FormParam("phoneNumber") int phoneNumber,
            @FormParam("emailAddress") String emailAddress,
            @FormParam("personnelnumber") String personnelnumber,
            @FormParam("password") String password,
            @FormParam("discriminator") String discriminator
            ) {
		
        User user = null;
        
        switch(discriminator) {
        case "EMPLOYE":
            user = new Employee();
            break;
        case "MANAGER":
            user = new Manager();
            break;
        case "WORKER":
            user = new Worker();
            break;
        }
        
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setSexe(sexe);
        user.setCity(city);
        user.setPostalCode(postalCode);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setPersonnelNumber(personnelnumber);
        user.setPassword(password);
        user.setDiscriminator(discriminator);
        user.setActive(true);
        
        conn = APIRestConnection.getInstance();
       
        boolean insert = new UserDAO(conn).create(id_site, user);
        
        if (insert)
            response = Response.status(Response.Status.CREATED).entity(true).build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
       
        return response;
    }
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(
            @FormParam("id") int id,
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("address") String address,
            @FormParam("dateOfBirth") Date dateOfBirth,
            @FormParam("sexe") char sexe,
            @FormParam("city") String city,
            @FormParam("postalCode") int postalCode,
            @FormParam("phoneNumber") int phoneNumber,
            @FormParam("emailAddress") String emailAddress,
            @FormParam("personnelnumber") String personnelnumber,
            @FormParam("password") String password,
            @FormParam("discriminator") String discriminator,
            @FormParam("active") boolean active
            ) {
		 
        User user = null;
        
        switch(discriminator) {
        case "EMPLOYE":
            user = new Employee();
            break;
        case "MANAGER":
            user = new Manager();
            break;
        case "WORKER":
            user = new Worker();
            break;
        }
        
        user.setId(id);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setSexe(sexe);
        user.setCity(city);
        user.setPostalCode(postalCode);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setPersonnelNumber(personnelnumber);
        user.setPassword(password);
        user.setDiscriminator(discriminator);
        user.setActive(active);
     
        conn = APIRestConnection.getInstance();
        
        boolean update = new UserDAO(conn).update(user);
        
        if (update)
            response = Response.status(Response.Status.OK).entity(true).build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
        
        return response;
    }
	
    @GET
    @Path("workers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(@PathParam("id") int id) {        
        conn = APIRestConnection.getInstance();
        
        ArrayList<Worker> workers = new WorkerDAO(conn).findall(id);
        
        if (workers != null)
            response = Response.status(Response.Status.OK).entity(workers).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).build();

        return response;
    }
}
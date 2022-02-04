package be.fabrictoutapi.api;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
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

@Path("/report")
public class ReportAPI {
	//Attributes/Variables
    private Connection conn;
    private Response response;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReports() {
        conn = APIRestConnection.getInstance();
        
        ArrayList<Report> reports = new ReportDAO(conn).findall();
        
        if (!reports.isEmpty())
            response = Response.status(Response.Status.OK).entity(reports).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).build();
    
        return response;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReport(@PathParam("id") int id) {        
        conn = APIRestConnection.getInstance();
       
        Report report = new ReportDAO(conn).find(id);

        if (report != null)
            response = Response.status(Response.Status.OK).entity(report).build();
        else
            response = Response.status(Response.Status.NO_CONTENT).build();

        return response;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReport(
            @FormParam("date") Date date,
            @FormParam("description") String description,
            @FormParam("id_worker") int id_worker,
            @FormParam("id_maintenance") int id_maintenance) {   
        Report r = new Report();
        r.setDate(date);
        r.setDescription(description);
       
        Worker w = new Worker();
        w.setId(id_worker);
       
        r.setWorker(w);
       
        conn = APIRestConnection.getInstance();
        
        boolean insert = new ReportDAO(conn).create(id_maintenance, r);
       
        if (insert)
            response = Response.status(Response.Status.CREATED).entity(true).build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity(false).build();
            
        return response;
    }
}
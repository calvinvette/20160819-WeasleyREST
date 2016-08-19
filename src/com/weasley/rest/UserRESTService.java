package com.weasley.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weasley.data.User;
import com.weasley.data.UserDAO;
import com.weasley.data.UserDAOMock;

// Class-level @Path appends to the @ApplicationPath (in CenturyLinkRESTAPP)
//http://myserver.com:8080/rest-demo/rest/user
@Path("/user")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class UserRESTService {

//	@Inject
	UserDAO userDAO = new UserDAOMock();

	@POST
	public User insert(User user) {
		Response.ok(userDAO.insert(user));
		return user;
	}

	@DELETE
	public User delete(User user) {
		return userDAO.delete(user);
	}

	@PUT
	public User update(User user) {
		return userDAO.update(user);
	}

	@GET
	//http://myserver.com:8080/rest-demo/rest/user/1234
	@Path("{id: \\d+}")
	public User findById(@PathParam("id") Long userNumber) {
		return userDAO.findById(userNumber);
	}

	@GET
	//http://myserver.com:8080/rest-demo/rest/user/username/hpotter
	@Path("/username/{userName}")
	public User findByUserName(@PathParam("userName") String userName) {
		return userDAO.findByUserName(userName);
	}

	@GET
	public List<User> findAll() {
		return userDAO.findAll();
	}
	
	
	
}

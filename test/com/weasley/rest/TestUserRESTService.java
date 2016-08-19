package com.weasley.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
//import java.util.concurrent.Future;
//import javax.ws.rs.client.InvocationCallback;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.weasley.data.User;

public class TestUserRESTService {
	private Client client;
	private static String BASE_URL = "http://localhost:8080/rest-demo/rest";
	private static String USER_BASE_URL = BASE_URL + "/user/";

	@Before
	public void setup() {
		client = ClientBuilder.newClient()
		// .register(JacksonFeature.class) // Jersey
		;
	}

	@Test
	public void testInsertCustomer() {
		User user = new User();
		user.setAge((short)37);
		user.setFirstName("Draco");
		user.setLastName("Malfoy");
		user.setEmail("draco@malfoy.co.uk");
		user.setUserName("dracom");
		Entity<User> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
		Response response = client.target(USER_BASE_URL).request(MediaType.APPLICATION_JSON).post(userEntity);
		dumpResponse(response);
	}

	@Test
	public void testGetAll() {
		Response response = client.target(USER_BASE_URL).request(MediaType.APPLICATION_JSON).get();

		dumpResponse(response);

		assertEquals("Wrong Type returned - expected JSON, got " + response.getHeaderString(HttpHeaders.CONTENT_TYPE),
				response.getHeaderString(HttpHeaders.CONTENT_TYPE), MediaType.APPLICATION_JSON);

		try {
			@SuppressWarnings("unchecked")
			List<User> usersFound = response.readEntity(List.class);
			assertTrue("No users found! Got: " + usersFound.size(), usersFound.size() > 0);
			System.out.println("Users: " + usersFound);
		} catch (Exception e) {
			System.out.println("Failed to read Entity object as List<User>: " + e.getMessage());
			fail("Exception blown on User List Entity Read");
			e.printStackTrace();
		}

		// Future<Response> responseFuture =
		// client
		// .target(USER_BASE_URL)
		// .request(MediaType.APPLICATION_JSON)
		// .get(new InvocationCallback<Response>() {
		// .async()
		// .get(new InvocationCallback<Response>() {
		// @Override
		// public void completed(Response response) {
		// assertEquals("Wrong Type returned - expected JSON, got " +
		// response.getHeaderString(HttpHeaders.CONTENT_TYPE),
		// response.getHeaderString(HttpHeaders.CONTENT_TYPE),MediaType.APPLICATION_JSON);
		// System.out.println("Entity: " + response.getEntity());
		// System.out.println("EntityTag: " + response.getEntityTag());
		// System.out.println("LastModified: " + response.getLastModified());
		// System.out.println("Length: " + response.getLength());
		// System.out.println("ContentType: " +
		// response.getHeaderString(HttpHeaders.CONTENT_TYPE));
		// System.out.println("MediaType: " + response.getMediaType());
		// System.out.println("Status: " + response.getStatus());
		// System.out.println("StatusInfo: " + response.getStatusInfo());
		// System.out.println("Location: " + response.getLocation());
		// }
		//
		// @Override
		// public void failed(Throwable response) {
		// fail("Get All Failed to return User List: " + response.getMessage());
		// }
		// });
	}

	private void dumpResponse(Response response) {
		System.out.println("Entity: " + response.getEntity());
		System.out.println("EntityTag: " + response.getEntityTag());
		System.out.println("LastModified: " + response.getLastModified());
		System.out.println("Length: " + response.getLength());
		System.out.println("ContentType: " + response.getHeaderString(HttpHeaders.CONTENT_TYPE));
		System.out.println("MediaType: " + response.getMediaType());
		System.out.println("Status: " + response.getStatus());
		System.out.println("StatusInfo: " + response.getStatusInfo());
		System.out.println("Location: " + response.getLocation());
	}
}

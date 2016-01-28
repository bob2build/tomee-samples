package com.walmart.move.nim.samples.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class Greeter {

	@Inject
	private Timer timer;

	@GET
	public String greet(@QueryParam("name") String name) {
		return String.format("Hello %s. The current time is %s", name,
				timer.getTime());
	}
}

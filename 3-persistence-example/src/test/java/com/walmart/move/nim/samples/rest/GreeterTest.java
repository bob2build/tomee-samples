package com.walmart.move.nim.samples.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GreeterTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class).addClass(Greeter.class)
				.addClass(Timer.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void testGreeter(@ArquillianResource URL resourceURL)
			throws Exception {
		WebClient client = WebClient.create(resourceURL.toURI()).query("name",
				"babu");
		Response r = client.accept(MediaType.TEXT_PLAIN_TYPE).get();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(InputStream) r.getEntity()));
		String responeStr = br.readLine();
		assertTrue(
				String.format("Response [%s] string not expected ", responeStr),
				responeStr.contains("Hello babu"));
		br.close();
	}
}

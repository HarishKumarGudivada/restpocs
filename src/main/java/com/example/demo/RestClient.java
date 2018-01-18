package com.example.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;

public class RestClient {

@SuppressWarnings("unchecked")
public static void main(String ... args) throws InterruptedException, ExecutionException {
	
	Client client=ClientBuilder.newClient();
	//Response response= client.target("http://localhost:8080/asynchronous/load").request().get();

	
	
	Future<Response> future= client.target("http://localhost:8080/asynchronous/loademployee").request().async().get(new InvocationCallback<Response>() {

		@Override
		public void completed(Response response) {
			System.out.println("Response code "+response.getStatus() );	
			
		}

		@Override
		public void failed(Throwable throwable) {
			System.out.println("Failed");
			throwable.printStackTrace();
		}
	});
	
	System.out.println(future.get().getEntity().toString());
	
	System.out.println("Hai ");
}
}

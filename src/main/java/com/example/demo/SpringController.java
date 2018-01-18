package com.example.demo;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

@Controller
@Path("/asynchronous")
public class SpringController {

	
	
	@GET
	@Path("/load")
	@Produces(value= {"application/json","application/xml"})
	public Response load() {
		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Response.ok().entity(new Employee(1,"Harish",20000)).build();
	}
	
	
	
	@GET
	@Async
	@Path("/loademployee")
	@Produces(value= {"application/vnd.versioning-v1.0+json","application/vnd.versioning-v1.0+xml"})
	public void loadEmployeeData(@Suspended AsyncResponse response) {
		
		response.setTimeout(20, TimeUnit.SECONDS);
		response.setTimeoutHandler((res)->{
			res.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("TIME OUT !").build());
		});
		
		//callback
		response.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable throwable) {
                if (throwable == null) {
                 System.out.println("success");
                } else {
                	System.out.println("error");
                }
            }
        }, new ConnectionCallback() {
            public void onDisconnect(AsyncResponse disconnected) {
            	System.out.println("disconnected");
            }
        });
		
		//end callback
		
		 Thread thread = new Thread(new Runnable() {
	            @Override
	            public void run() {
						try {
							Thread.sleep(15000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Employee result = heavyLifting();
		            	response.resume(Response.status(Status.OK).entity(result).build());
				}
	            private Employee heavyLifting() {
	                return new Employee(1,"Harish",20000);
	            }
	        });
	        thread.start();
	       
	}
	
}

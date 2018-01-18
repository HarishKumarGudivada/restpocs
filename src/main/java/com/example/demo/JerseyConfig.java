package com.example.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
 
@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
    	//packages("com.nisum.jersey.springjersey");
        register(SpringController.class);

        register(GenericExceptionMapper.class);
    }
}

package com.panospost.config;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.logging.Logger;

public class LoggerConfig {

    @Context
    SecurityContext securityContext;

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


}

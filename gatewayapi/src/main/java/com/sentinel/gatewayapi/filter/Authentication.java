package com.sentinel.gatewayapi.filter;

import com.sentinel.lib.JWT.Jwt;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class Authentication extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(Authentication.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        System.out.println("TEST");
        HttpServletRequest request = ctx.getRequest();
        System.out.println("TEST1");
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        /*
        RequestContext context = RequestContext.getCurrentContext();
        System.out.println(context);
        try {
            String jwt = Jwt.generateToken();
            log.info("Adding JWT token %s to 'Authentication' header.", jwt);
            context.addZuulRequestHeader("Authentication", jwt);
            return null;
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        */

        return null;
    }
}

package com.sentinel.gatewayapi.filter;

import com.sentinel.lib.JWT.Jwt;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        RequestContext context = RequestContext.getCurrentContext();

        System.out.println("TEST");
        System.out.println(context);

        try {
            String jwt = Jwt.generateToken();

            context.addZuulRequestHeader("Authentication", jwt);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }
}

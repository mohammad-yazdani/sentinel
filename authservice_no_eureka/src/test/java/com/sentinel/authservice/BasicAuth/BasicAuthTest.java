package com.sentinel.authservice.BasicAuth;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicAuthTest {

    private static Logger log = LoggerFactory.getLogger(BasicAuthTest.class);

    @Test
    public void Register () {
        // TODO : Test client registration.
        String url = "http://localhost:8082/auth/register";

        HttpUriRequest httpUriRequest = new HttpPost(url);
        httpUriRequest.setHeader("username", "mohammad");
        httpUriRequest.setHeader("password", "mohammad");
        httpUriRequest.setHeader("email", "mohammad");

        try {

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpUriRequest);
            log.info(httpResponse.toString());
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void Login () {
        // TODO : Test client login.
        String url = "http://localhost:8082/auth/login";

        HttpUriRequest httpUriRequest = new HttpGet(url);
        httpUriRequest.setHeader("username", "mohammad");
        httpUriRequest.setHeader("password", "mohammad");

        try {

            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpUriRequest);
            log.info(httpResponse.toString());
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}

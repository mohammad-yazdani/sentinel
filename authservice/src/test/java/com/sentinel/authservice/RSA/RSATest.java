package com.sentinel.authservice.RSA;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RSATest {

    private static Logger log = LoggerFactory.getLogger(RSA.class);

    @Autowired
    private RSA rsa = RSA.getInstance();

    @Test
    public void Simple() {
        if (!rsa.GenerateKeyPair()) {
            log.error("Could not generate RSA key pair.");
        }
        else {
            log.info("RSA Key pair generated.");
        }
    }
}
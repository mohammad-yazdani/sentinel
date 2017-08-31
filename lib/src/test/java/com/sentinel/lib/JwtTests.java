package com.sentinel.lib;

import com.sentinel.lib.JWT.Jwt;
import org.junit.Test;


public class JwtTests {

    @Test
    public void Public () {
        try {
            System.out.println(Jwt.getPublicKey());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void Private () {
        try {
            System.out.println(Jwt.getPrivateKey());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

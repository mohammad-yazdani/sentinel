package com.sentinel.lib;

import com.sentinel.lib.JWT.Jwt;
import org.junit.Test;


public class JwtTests {

    @Test
    public void Generate () {
        try {
            System.out.println(Jwt.generateToken());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void Verify () {
        try {

            System.out.println(Jwt.verifyToken(Jwt.generateToken()));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

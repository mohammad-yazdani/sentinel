package com.sentinel.lib;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;

public class Jwt {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(Jwt.class);

    public static String verfiy () {
        return null;
    }

    public static String generate () {

        String token = null;
        ArrayList<Key> keys = Jwt.LoadKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keys.get(0);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keys.get(1);
        Algorithm algorithmRS = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

        // TODO : INCOMPLETE
        token = JWT.create()
                .withIssuer("sentinel")
                .sign(algorithmRS);

        return token;
    }

    private static ArrayList<Key> LoadKeyPair() {
        ArrayList<Key> keyArrayList = null;
        try {
            // Read Public Key.
            File filePublicKey = new File("keys/public");
            FileInputStream fis = new FileInputStream("keys/public");
            byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
            fis.read(encodedPublicKey);
            fis.close();

            // Read Private Key.
            File filePrivateKey = new File("keys/private");
            fis = new FileInputStream("keys/private");
            byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
            fis.read(encodedPrivateKey);
            fis.close();

            // Generate KeyPair.
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                    encodedPublicKey);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            publicKey = (RSAPublicKey) publicKey;

            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
                    encodedPrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
            privateKey = (RSAPrivateKey) privateKey;

            keyArrayList.add(publicKey);
            keyArrayList.add(privateKey);
        }
        catch (IOException e) {
            log.error("Could not retrieve RSA key pair for JWT generation:");
            log.error(e.getMessage());
        }
        catch (NoSuchAlgorithmException e) {
            log.error("Error in finding RSA key algorithm:\n" + e.getMessage());
        }
        catch (InvalidKeySpecException e) {
            log.error("Error in RSA key pair specs:\n" + e.getMessage());
        }
        return keyArrayList;
    }
}

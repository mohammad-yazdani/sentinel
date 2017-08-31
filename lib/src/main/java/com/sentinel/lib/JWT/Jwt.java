package com.sentinel.lib.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Jwt {

    //private static Logger log = LoggerFactory.getLogger(Jwt.class);

    public static RSAPublicKey getPublicKey () throws Exception {

        try {
            /*InputStream is = new FileInputStream("../shared/publicKey");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while(line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String fileAsString = sb.toString();
            System.out.println("Contents : " + fileAsString);*/

            // String publicKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("../shared/publicKey").toURI())));
            String publicKeyContent = new String(Files.readAllBytes(Paths.get("../shared/publicKey")));
            System.out.println(publicKeyContent);
            publicKeyContent = publicKeyContent.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");;
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
            RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);

            System.out.println(pubKey);
            return pubKey;
        }
        catch (Exception e) {
            //log.error(e.getMessage());
            throw e;
        }
        /*
        catch (URISyntaxException e) {
            log.error(e.getMessage());
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
        catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        catch (InvalidKeySpecException e) {
            log.error(e.getMessage());
        }
        */
    }

}

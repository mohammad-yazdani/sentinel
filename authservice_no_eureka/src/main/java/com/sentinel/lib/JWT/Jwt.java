package com.sentinel.lib.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

public class Jwt {

    private static RSAPublicKey getPublicKey () throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        String publicKeyPEM = FileUtils.readFileToString(new File("../shared/publickey.pem"), StandardCharsets.UTF_8);

        // strip of header, footer, newlines, whitespaces
        publicKeyPEM = publicKeyPEM
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        // decode to get the binary DER representation
        byte[] publicKeyDER = Base64.getDecoder().decode(publicKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyDER));
    }

    private static RSAPrivateKey getPrivateKey () throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyPEM = FileUtils.readFileToString(new File("../shared/privatekey-pkcs8.pem"), StandardCharsets.UTF_8);

        // strip of header, footer, newlines, whitespaces
        privateKeyPEM = privateKeyPEM
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        // decode to get the binary DER representation
        byte[] privateKeyDER = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyDER));
    }

    public static String generateToken (String issuedTo) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JWTCreationException {
        Algorithm algorithm = Algorithm.RSA256(Jwt.getPublicKey(), Jwt.getPrivateKey());
        return JWT.create()
                .withIssuer("sentinel")
                .withKeyId(issuedTo)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public static DecodedJWT verifyToken (String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JWTVerificationException {
        Algorithm algorithm = Algorithm.RSA256(Jwt.getPublicKey(), Jwt.getPrivateKey());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("sentinel")
                .build();
        return verifier.verify(token);
    }

}
